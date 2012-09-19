class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/$usuario"(controller:"perfil", action:"profileUsuario");
		"/" (controller: "perfil", action:"index")
		"500"(view:'/error')
	}
}
