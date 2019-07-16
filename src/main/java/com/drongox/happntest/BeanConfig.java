package com.drongox.happntest;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig
{
  @Bean
  public TsvParser getTsvParser()
  {
    TsvParserSettings settings = new TsvParserSettings();
    settings.getFormat().setLineSeparator("\n");
    return new TsvParser(settings);
  }
}
