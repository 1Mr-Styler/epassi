package com.jerry.epassi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WordCountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetKMostOccurringWords() throws Exception {
        String content = "How many Cats is enough Cats? There is no such thing as too many Cats";
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", content.getBytes());

        mockMvc.perform(multipart("/counter")
                        .file(file)
                        .param("k", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].word").value("cats"))
                .andExpect(jsonPath("$[0].count").value(3))
                .andExpect(jsonPath("$[1].word").value("many"))
                .andExpect(jsonPath("$[1].count").value(2));
    }
}
