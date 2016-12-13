class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/categoria/$nombreCategoria"(controller:"perfil", action:"categoria");
		"/$usuario"(controller:"perfil", action:"profileUsuario");
		"/" (controller: "perfil", action:"principal")
		"500"(view:'/error')
		"/prueba"(controller: "perfil", action:"principal2")
	}
}
