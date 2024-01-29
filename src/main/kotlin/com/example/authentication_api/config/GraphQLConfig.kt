package com.example.gitauthentication_api.config

import com.coxautodev.graphql.tools.SchemaParser
import com.example.authentication_api.resolver.UserMutationResolver
import com.example.authentication_api.resolver.UserResolver
import graphql.schema.GraphQLSchema
import graphql.servlet.SimpleGraphQLHttpServlet
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@ServletComponentScan
class GraphQLConfig(private val userQueryResolver: UserResolver,
                    private val userMutationResolver: UserMutationResolver) {


    @Bean
    fun graphQLServlet(): ServletRegistrationBean<*> {
        val schema: GraphQLSchema = SchemaParser.newParser()
            .file("graphql/schema.graphqls")
            .resolvers(userQueryResolver, userMutationResolver)
            .build()
            .makeExecutableSchema()

        return ServletRegistrationBean(SimpleGraphQLHttpServlet.newBuilder(schema).build(), "/graphql")
    }
}