/*
 *
 *  * Copyright 2019-2020 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package test.org.springdoc.api.app8

import org.springdoc.core.utils.Constants
import org.springframework.aop.framework.ProxyFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.support.GenericApplicationContext
import org.springframework.test.context.TestPropertySource
import test.org.springdoc.api.AbstractKotlinSpringDocMVCTest

@TestPropertySource(properties =  [Constants.SPRINGDOC_NULLABLE_REQUEST_PARAMETER_ENABLED+"=false"])
class SpringDocApp8Test : AbstractKotlinSpringDocMVCTest() {

	@SpringBootApplication
	@ComponentScan(basePackages = ["org.springdoc", "test.org.springdoc.api.app8"])
	class DemoApplication{
		@Bean
		fun controller(applicationContext: GenericApplicationContext): ExampleController {
			return createProxy(ExampleController::class.java)
		}

		private fun <T> createProxy(clazz: Class<T>): T {
			val proxyFactory = ProxyFactory(clazz)
			proxyFactory.targetClass = clazz
			@Suppress("UNCHECKED_CAST")
			return proxyFactory.proxy as T
		}
	}

}
