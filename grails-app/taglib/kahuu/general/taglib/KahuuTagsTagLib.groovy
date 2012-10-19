package kahuu.general.taglib

class KahuuTagsTagLib
{

	def lines = 
	{ attrs, body ->
		 out << attrs['string'].encodeAsHTML().replace('\n', '<br/>\n')
	}
	
	

}
