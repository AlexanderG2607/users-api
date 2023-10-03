package com.globalogic.bci.usersapi;



//@SpringBootTest
//@ActiveProfiles("test")
class UsersApiApplicationTests {

//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	def "should sign up a user successfully"() {
//		given:
//		CreateUserRequestDTO validRequest = createValidRequest();
//
//		when:
//		def response = mockMvc.perform(post("/sign-up")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(validRequest)))
//				.andReturn().response
//
//		then:
//		response.status == HttpStatus.CREATED.value()
//		response.jsonPath().getString("id") != null
//		response.jsonPath().getString("created") != null
//		response.jsonPath().getString("token") != null
//		response.jsonPath().getBoolean("isActive") == true
//		response.jsonPath().getString("name") == validRequest.getName()
//		response.jsonPath().getString("email") == validRequest.getEmail()
//		response.jsonPath().getList("phones").size() == 1
//		response.jsonPath().getInt("phones[0].number") == validRequest.getPhones().get(0).getNumber()
//		response.jsonPath().getInt("phones[0].cityCode") == validRequest.getPhones().get(0).getCityCode()
//		response.jsonPath().getString("phones[0].countryCode") == validRequest.getPhones().get(0).getCountryCode()
//	}
//
//	def "should handle invalid email format"() {
//		given:
//		CreateUserRequestDTO invalidEmailRequest = createValidRequest(email: "invalid_email")
//
//		when:
//		def response = mockMvc.perform(post("/sign-up")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(invalidEmailRequest)))
//				.andReturn().response
//
//		then:
//		response.status == HttpStatus.BAD_REQUEST.value()
//	}
//
//	def "should handle invalid password format"() {
//		given:
//		CreateUserRequestDTO invalidPasswordRequest = createValidRequest(password: "invalid_password")
//
//		when:
//		def response = mockMvc.perform(post("/sign-up")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(invalidPasswordRequest)))
//				.andReturn().response
//
//		then:
//		response.status == HttpStatus.BAD_REQUEST.value()
//	}
//
//	// Add more tests for optional fields, error messages, etc.
//
//	private CreateUserRequestDTO createValidRequest(Map<String, ?> overrides) {
//		def defaultRequest = [
//		name: "Alex",
//				email: "lex.184.19r33se5s315@gmail.com",
//				password: "a2asfGfdfdf4",
//				phones: [
//                [number: 1234, cityCode: 2, countryCode: "ARG"]
//            ]
//        ]
//		return new CreateUserRequestDTO(defaultRequest + overrides)
//	}
//}

}
