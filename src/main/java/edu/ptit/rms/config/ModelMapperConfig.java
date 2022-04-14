package edu.ptit.rms.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    // all fields name must in order
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

    /* custom mapping */

    return modelMapper;
  }
}
