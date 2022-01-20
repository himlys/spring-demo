<html>
<!-- 不为空，就打印-->
${user.name?if_exists}
${user.name!}
<script>
    // 注意，这里不能这么用上面的两种形式，不然变量声明后没东西，js就报错了。
    var userName = ${user.name};
    console.log(userName);
</script>
<!-- 拼接字符串-->
${"hello${user.name!}"}
${"hello"+user.name!}
<!-- 声明变量-->
<#assign str = "0123456789"/>
<!--截取字符串-->
${str?substring(0,4)}
${str[0]}${str[6]}
${str[1..3]}
<!--日期格式format-->
${userdate?string('yyyy-MM-dd')}
<!--条件判断-->
<#if str?length == 10 >
    中文
<#elseif str?length == 8>
    yingwen
<#elseif str?length == 1>
    latin
<#else>
    ????
</#if>
<!--include-->
<#include "/abc.ftlh" encoding="UTF-8" parse=true>

<!--两种循环-->
<#list userList! as user>
    <!--判断用户是否为空-->
    <#if user??>
        ${user.name}
    <#else>
    </#if>
</#list>
<br>
<#list userList2! as user>
    <!--判断用户和name属性是否为空-->
    <#if (user.name)??>
        ${user.name}
    <#else>
        ${user.id}
    </#if>
</#list>
<!--判断list是否为null，list中是否有记录-->
<#if userList3?? && (userList3?size > 0) >
    <h1>List不为空</h1>
    <#list userList as uInfo>
    </#list>
<#else>
    <h1>显示</h1>
</#if>
<!--Map遍历value-->
<#list userMap?values as value>
    ${value}
</#list>
<!--Map遍历-->
<#list userMap?keys as key>
    key:${key}--value:${userMap["${key}"]}
</#list>
</html>