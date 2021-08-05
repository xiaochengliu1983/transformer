package com.example.transformer;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.transformer.model.Transformer;
import com.example.transformer.service.TransformerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
class TransformerApplicationTests {
	   
    @Autowired                           
    private MockMvc mockMvc;  
                                                 
    @MockBean
	private TransformerService serviceMock;
    
    private List<Transformer> transformers; 
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @BeforeEach                           
    void setUp() {        
        this.transformers = new ArrayList<>();                                    
        this.transformers.add(new Transformer("Soundwave", "D", 8,9,2,6,7,5,6,10));                               
        this.transformers.add(new Transformer("Bluestreak", "A", 6,6,7,9,5,2,9,7));                               
        this.transformers.add(new Transformer("Hubcap", "A", 4,4,4,4,4,4,4,4));
               
    }

    @Test
    void shouldFetchAllTransformer() throws Exception {
    	when(serviceMock.findAll()).thenReturn(transformers);
        this.mockMvc.perform(get("/transformers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(transformers.size())));
    }
    
    @Test
    void shouldCreateNewTransformer() throws Exception {
    	Transformer transformer = new Transformer("Soundwave", "D", 9,9,3,6,7,5,7,1);
    	when(serviceMock.save(any(Transformer.class))).thenReturn(new Transformer("Soundwave", "D", 9,9,3,6,7,5,7,1));

        this.mockMvc.perform(post("/transformers")
                .content(objectMapper.writeValueAsString(transformer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(transformer.getName())))
                .andExpect(jsonPath("$.type", is(transformer.getType())))
        ;
    }

    @Test
    void shouldUpdateTransformer() throws Exception {
    	Transformer transformer = new Transformer("Soundwave", "D", 9,9,3,6,7,5,7,1);
    	when(serviceMock.save(any(Transformer.class))).thenReturn(new Transformer("Soundwave", "D", 9,9,3,6,7,5,7,1));

        this.mockMvc.perform(put("/transformers")
                .content(objectMapper.writeValueAsString(transformer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(transformer.getName())))
                .andExpect(jsonPath("$.type", is(transformer.getType())))
        ;
    }
    
    @Test
    void shouldDeleteTransformer() throws Exception {
    	Transformer transformer = new Transformer("Soundwave", "D", 9,9,3,6,7,5,7,1);
    	
        this.mockMvc.perform(delete("/transformers")
                .content(objectMapper.writeValueAsString(transformer)))
                .andExpect(status().isOk())
        ;
    }
    
    
    @Test
    void shouldReturnWinningTransformer() throws Exception {   
    	
    	MvcResult mvcResult = this.mockMvc.perform(post("/transformers/battle")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(objectMapper.writeValueAsString(transformers)))
                .andExpect(status().isOk())
                .andReturn()
        ;

        String responseBody = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(responseBody).isEqualTo("1 battle Winning team (Decepticons): Soundwave Survivors from the losing team (Autobots): Hubcap");
    }
    
    @Test
    void shouldReturnOptimusPrime() throws Exception {   
    	
    	List<Transformer> t1 = new ArrayList<>();                                    
        t1.add(new Transformer("Soundwave", "D", 8,9,2,6,7,5,6,10));                               
        t1.add(new Transformer("Optimus Prime", "A", 6,6,7,9,5,2,9,7));                               
        
    	MvcResult mvcResult = this.mockMvc.perform(post("/transformers/battle")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(objectMapper.writeValueAsString(t1)))
                .andExpect(status().isOk())
                .andReturn()
        ;

        String responseBody = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(responseBody).isEqualTo("1 battle Winning team (Autobots): Optimus Prime Survivors from the losing team (Decepticons): ");
    }
    
    @Test
    void shouldReturnPredaking() throws Exception {   
    	
    	List<Transformer> t1 = new ArrayList<>();                                    
        t1.add(new Transformer("Predaking", "D", 8,9,2,6,7,5,6,10));                               
        t1.add(new Transformer("Bluestreak", "A", 6,6,7,9,5,2,9,7));                               
        
    	MvcResult mvcResult = this.mockMvc.perform(post("/transformers/battle")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(objectMapper.writeValueAsString(t1)))
                .andExpect(status().isOk())
                .andReturn()
        ;

        String responseBody = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(responseBody).isEqualTo("1 battle Winning team (Decepticons): Predaking Survivors from the losing team (Autobots): ");
    }
}
