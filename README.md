# jmx-smart-replacer

##Changers.removeAllQueryNumbersFromTestName(filePath);
`- removes all [\d+] lexemes from the name of test`

##Changers.removeAllComments(filePath);
`- removes all comments with format "-- QUERY \d+"`

##Changers.addQuerryNumberToTestName(filePath);
`- adds [n] lexemes from the name of test; n - number of statement`

##Changers.addCommentWithQueryNumber(filePath);
`- adds comments with format "-- QUERY n"; n - number of statement`

##Changers.shrinkSamplerNames(filePath, filePath);
`- use it to shortify the names of tests.`
