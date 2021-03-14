package com.legal_diary_app;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/cucumber-html-report"},
        features = {"classpath:features"},
        glue = {"com.legal_diary_app.steps"},
        snippets = SnippetType.CAMELCASE)
public class LaunchTest{
}
