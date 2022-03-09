package me.lozm.api.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.lozm.api.global.restDocs.RestDocsConfiguration;
import me.lozm.api.service.UserService;
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
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static me.lozm.api.global.restDocs.ApiDocumentUtils.getDocumentRequest;
import static me.lozm.api.global.restDocs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;


    @Test
    void getUserDetailTest() throws Exception {
        //Given

        final Long userId = 1L;
        Mockito.when(userService.getUserDetail(Mockito.any()))
                .thenReturn(UserInfoVo.of(userId, "laonzenamoon@gmail.com", "junlee", List.of()));

        //When
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/users/{userId}", userId)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //Then
        result.andExpect(status().is(200))
                .andDo(MockMvcRestDocumentationWrapper.document("get-user-detail",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("userId").description("사용자 ID")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("API 호출 성공여부"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("API 호출 코드"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("API 호출 메시지"),
                                fieldWithPath("orders[]").type(JsonFieldType.ARRAY).description("API 호출 데이터")
                        )
                ));
    }

}