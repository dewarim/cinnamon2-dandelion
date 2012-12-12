class UrlMappings {
    static mappings = {
      "/" {
    	  controller = 'login'
    	  action = 'auth'
      }
     
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
	  "500"(
              view:'/error'
      )
	}
}
