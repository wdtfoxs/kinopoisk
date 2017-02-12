<#include "template.ftl">
<@mainTemplate title="Page"/>
<#macro m_body>
<script src="../../resources/js/ajax.js"></script>
<div class="content">
    <div class="register">
        <input type="text" name="finder" id="finder" oninput="get()">
        <div id="text"></div>
        <#--<input type="submit" value="send">-->
    </div>
</div>
</#macro>