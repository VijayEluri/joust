def appHome = System.properties.getProperty('appHome')
if (!appHome) {
	println """
================
 CORRECT USAGE EXAMPLE:
	 
 groovy -DappHome=c:/Projects/myApp JspExpressionLanguageExtractor.groovy
================
 """
}

def printUniqueMatches = { matcher ->
	def uniques = [:]
	matcher.each { 
		uniques.put(it[0],"")
	}
	uniques.each {
		println "    $it.key"
	}
}

new File("$appHome/src/main/webapp").eachFileRecurse {
	def file = it
	if (file.file && file.name.endsWith(".jsp")) {
		elMatcher = file.text =~ /\$\{(.)*\}/
		pathMatcher = file.text =~ /path=\"(.)*\"/
		if (elMatcher || pathMatcher) {
			println file.name
			printUniqueMatches elMatcher
			printUniqueMatches pathMatcher
		}
	}
}