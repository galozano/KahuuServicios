package kelgal.empleos

class KahuuTagsTagLib
{

	def lines = 
	{ attrs, body ->
		 out << attrs['string'].encodeAsHTML().replace('\n', '<br/>\n')
	}

}
