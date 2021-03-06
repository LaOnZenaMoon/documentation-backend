package me.lozm.api.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.lozm.api.global.restDocs.RestDocsConfiguration;
import me.lozm.api.service.UserService;
import me.lozm.user.vo.UserCreateVo;
import me.lozm.user.vo.UserInfoVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class UserControllerTest {

    private final Long USER_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;


    @Test
    void getUserDetailTestSuccess() throws Exception {
        //Given
        Mockito.when(userService.getUserDetail(Mockito.any()))
                .thenReturn(UserInfoVo.of(USER_ID, "laonzenamoon@gmail.com", "junlee", List.of()));

        //When
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/users/{userId}", USER_ID)
        );

        //Then
        result.andExpect(status().is(HttpStatus.OK.value()))
                .andDo(MockMvcRestDocumentationWrapper.document("get-user-detail", "????????? ?????? ??????",
                        pathParameters(
                                parameterWithName("userId").description("????????? ID")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("????????? ID"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("??????"),
                                fieldWithPath("orders[]").type(JsonFieldType.ARRAY).description("?????? ??????")
                        )
                ));
    }

    @Test
    void getUserDetailTestBadRequest() throws Exception {
        //Given

        //When
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/users/{userId}", USER_ID)
        );

        //Then
        result.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andDo(MockMvcRestDocumentationWrapper.document("get-user-detail-error", "????????? ?????? ??????",
                        pathParameters(
                                parameterWithName("userId").description("????????? ID")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("?????? ?????????")
                        )
                ));
    }

    @Test
    void createUserTest() throws Exception {
        // Given
        String requestJson = "{\n" +
                "  \"email\": \"laonzenamoon@gmail.com\",\n" +
                "  \"name\": \"JUN LEE\",\n" +
                "  \"password\": \"asdfasdf1234\"\n" +
                "}";

        Mockito.when(userService.createUser(Mockito.any()))
                .thenReturn(UserCreateVo.of(USER_ID, "laonzenamoon@gmail.com", "junlee", "asdfasdf1234", "asdfasdf1234"));

        // When
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/users")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.GuoUe6tw79bJlbU1HU0ADX0pr0u2kf3r_4OdrDufSfQ")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        );

        // Then
        result.andExpect(status().is(HttpStatus.CREATED.value()))
                .andDo(MockMvcRestDocumentationWrapper.document("create-user", "????????? ??????", resource(
                                ResourceSnippetParameters.builder()
                                        .tags("??????")
                                        .description("JWT ?????? ?????????")
//                                        .requestHeaders(headerWithName(HttpHeaders.AUTHORIZATION).description("JWT"))
                                        .requestFields(
                                                fieldWithPath("email").type(JsonFieldType.STRING).description("?????????"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("password").type(JsonFieldType.STRING).description("????????????"))
                                        .responseFields(
                                                fieldWithPath("createdDateTime").type(JsonFieldType.STRING).description("????????????").optional(),
                                                fieldWithPath("lastModifiedDateTime").type(JsonFieldType.STRING).description("????????? ????????????").optional(),
                                                fieldWithPath("createdBy").type(JsonFieldType.STRING).description("?????????").optional(),
                                                fieldWithPath("lastModifiedBy").type(JsonFieldType.STRING).description("????????? ?????????").optional(),
                                                fieldWithPath("use").type(JsonFieldType.BOOLEAN).description("?????? ??????").optional(),
                                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("????????? ID"),
                                                fieldWithPath("email").type(JsonFieldType.STRING).description("?????????"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("??????"))
                                        .build()
                        )
                ));
    }

}