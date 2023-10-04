import com.fasterxml.jackson.databind.ObjectMapper
import com.globalogic.bci.usersapi.dto.CreateUserRequestDTO
import com.globalogic.bci.usersapi.dto.CreateUserResponseDTO
import com.globalogic.bci.usersapi.dto.ErrorResponseDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.boot.test.context.SpringBootTest

@AutoConfigureMockMvc
@SpringBootTest(classes= com.globalogic.bci.usersapi.UsersApiApplication.class)
@ActiveProfiles("test")
class UsersSpecification extends Specification {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    def "Name"() {
    }

    def "should sign up a user successfully"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.CREATED.value()
        def responseDTO = objectMapper.readValue(response.getContentAsString(), CreateUserResponseDTO.class)
        responseDTO.id != null
        responseDTO.created != null
        responseDTO.token != null
        responseDTO.isActive != null

        if(Objects.nonNull(validRequest.name)){
            responseDTO.name != null
        }

        responseDTO.email != null
        responseDTO.phones.size() > 0
    }


    def "email has no '@' returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([email: "otro.emailexample.com"])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("email")
    }

    def "email has no domain has no '.' returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([email: "otro.email@examplecom"])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("email")
    }

    def "email not present returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = new CreateUserRequestDTO([
                name: "Alex",
                password: "a2asfGfdfdf4",
                phones: [
                        [number: 11345666, cityCode: 2, countryCode: "3"]
                ]
        ])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("email")
    }

    def "email is null returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([email: null])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("email")
    }

    def "email is empty returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([email: ""])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("email")
    }

    def "email is blank returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([email: " "])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("email")
    }

    def "email has special character returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([email: "lex.184.19r!&%33se5s315@gmail.com"])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("email")
    }

    def "email has special character returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([email: "lex.184.19r!&%33se5s315@gmail.com"])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("email")
    }

    def "password has 7 characters returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([password: "a2asfG4", email: generateRandomEmailAddress()])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("password")
    }

    def "password has 13 characters returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([password: "a2asfGfdfdf4a", email: generateRandomEmailAddress()])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("password")
    }

    def "password has no upperCase character returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([password: "a2asfafdfdf4a", email: generateRandomEmailAddress()])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("password")
    }

    def "password has more than one upperCase character returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([password: "a2asfGfdfdF4", email: generateRandomEmailAddress()])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("password")
    }

    def "password has more than one upperCase character returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([password: "a2asfGfdfdF4", email: generateRandomEmailAddress()])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("password")
    }

    def "password has no numbers in it returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([password: "abasfGfdfdFh", email: generateRandomEmailAddress()])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("password")
    }

    def "password has more than 2 numbers in it returns 400 BAD_REQUEST"() {
        given:
        CreateUserRequestDTO validRequest = createValidRequest([password: "a2a5fGfdfdf4", email: generateRandomEmailAddress()])

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/users-api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
        def errorResponseDTO = objectMapper.readValue(response.getContentAsString(), ErrorResponseDTO.class)
        errorResponseDTO.errors.size() > 0
        errorResponseDTO.errors.get(0).detail.contains("password")
    }

    def createValidRequest(overrides) {
        def defaultRequest = [
                name: "Alex",
                email: "lex.184.19r33se5s315@gmail.com",
                password: "a2asfGfdfdf4",
                phones: [
                        [number: 11345666, cityCode: 2, countryCode: "3"]
                ]
        ]
        new CreateUserRequestDTO(defaultRequest + overrides ?: [:])
    }

    def generateRandomEmailAddress() {
        def longitudNombre = 8
        def caracteres = "abcdefghijklmnopqrstuvwxyz"
        def random = new Random()

        def nombreAleatorio = (0..<longitudNombre).collect { caracteres[random.nextInt(caracteres.length())] }.join()

        def dominio = "example.com"

        return "${nombreAleatorio}@${dominio}"
    }


}
