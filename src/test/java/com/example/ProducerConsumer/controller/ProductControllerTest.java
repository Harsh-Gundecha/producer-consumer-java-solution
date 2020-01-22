package com.example.ProducerConsumer.controller;

import com.example.ProducerConsumer.entity.FastFood;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @RepeatedTest(20)
    public void getOrderTest() throws Exception {
        int status = this.mockMvc.perform(get("/order")).andReturn().getResponse().getStatus();
        assertTrue(status == 200 || status == 404);
    }

    @RepeatedTest(20)
    public void postPizzaOrderTest() throws Exception {
        assertTrue(200 == this.mockMvc.perform(
                post("/order/pizza")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"cheesy onion\",\n" +
                                "    \"isVeg\": \"true\",\n" +
                                "    \"size\": \"7\"\n" +
                                "}")
                )
                .andReturn()
                .getResponse()
                .getStatus()
        );
    }

    @RepeatedTest(20)
    public void postBurgerOrderTest() throws Exception {
        assertTrue(200 == this.mockMvc.perform(
                post("/order/pizza")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"cheesy burger\",\n" +
                                "    \"isVeg\": \"true\",\n" +
                                "    \"size\": \"jumbo\"\n" +
                                "}")
                )
                        .andReturn()
                        .getResponse()
                        .getStatus()
        );
    }

}
