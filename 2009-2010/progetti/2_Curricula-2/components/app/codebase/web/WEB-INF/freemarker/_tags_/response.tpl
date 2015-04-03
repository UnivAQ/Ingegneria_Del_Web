[#macro do title="Curricul@ - Trova lavoro più facilmente" lang="IT"]
[#include '/_blocks_/document-xmldeclaration.tpl']
[#include '/_blocks_/document-doctype-xhtml-5.tpl']
[#-- [#include '/_blocks_/document-doctype-xhtml-1_1.tpl'] --]
[@xhtml.do lang="${lang}"]
    <head>
        <title>${title}</title>
        [#-- [#include '/_blocks_/head-metacontent-html.tpl'] --]
        [#-- [#include '/_blocks_/head-metacontent-xhtml.tpl'] --]
        [#include '/_blocks_/head-scripts.tpl']
        [#include '/_blocks_/head-styles.tpl']
    </head>
    <body>
        [#nested]
    </body>
[/@xhtml.do]
[/#macro]