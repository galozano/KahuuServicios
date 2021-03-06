// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

//Configure google Analytics
google.analytics.webPropertyID = "UA-34823980-1"

environments {
    development 
	{
        grails.logging.jul.usebridge = true
		
		//Facebook Desarrollo
		grails.plugin.facebooksdk.app.id = 389473204484178
		grails.plugin.facebooksdk.app.permissions = ['email']
		grails.plugin.facebooksdk.app.secret = 'a68c2ca8fdb37ef1c83a7991052559ab'
    }
	test {
		//Facebook Test
		grails.plugin.facebooksdk.app.id = 389473204484178
		grails.plugin.facebooksdk.app.permissions = ['email']
		grails.plugin.facebooksdk.app.secret = 'a68c2ca8fdb37ef1c83a7991052559ab'
	}
    production {
        grails.logging.jul.usebridge = false
		
		//Facebook Production
		grails.plugin.facebooksdk.app.id = 419788201444870
		grails.plugin.facebooksdk.app.permissions = ['email']
		grails.plugin.facebooksdk.app.secret = '8a23f7d3b576c9820ac38700f9e1762b'
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}


// log4j configuration
log4j = {	
	appenders {		
		environments {
			development { 
				console name: 'stdout', layout: pattern(conversionPattern: '%d %p %c{3} %m%n')
			}
			test { 
				console name: 'stdout', layout: pattern(conversionPattern: '%d %p %c{3} %m%n')
			}
			production {
				def catalinaBase = System.properties.getProperty('catalina.base')
				if (!catalinaBase) catalinaBase = '.'
				def logDirectory = "${catalinaBase}/logs"

				rollingFile name: 'file', file: "${logDirectory}/${appName}.log", layout: pattern(conversionPattern: '%d %p %c{3} %m%n'), maxFileSize:1024
				rollingFile name: 'StackTrace', file: "${logDirectory}/${appName}-stacktrace.log"
			}
		}
	}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
	
	environments {
		development { 
			//root { info 'stdout' } 
			debug   'grails.app.controllers'
			debug   'grails.app.domain'
			debug   'grails.app.services'										
		}
		test { 
			root { debug 'stdout' } 
		}
		production { 
			root { error 'file' } 
		}
	}
}


grails {
mail {
	host = "smtp.gmail.com"
	port = 465
	username = "soporte@kahuu.co"
	password = "so.52488"
	props = ["mail.smtp.auth":"true",
			 "mail.smtp.socketFactory.port":"465",
			 "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
			 "mail.smtp.socketFactory.fallback":"false"]
  }
}

//Constates de la base de datos
comun.perfiles.normal = "Normal"

