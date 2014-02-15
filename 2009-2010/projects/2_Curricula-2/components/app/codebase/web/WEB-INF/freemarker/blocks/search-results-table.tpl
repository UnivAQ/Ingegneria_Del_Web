[#if results??]
<table>
    <tbody
        [#list results as result]
        <tr>
            <td>
                <a href="${@.context}/view/${result.user_id}/">${result.name}</a>
            </td>
            <td>
                <a href="${@.context}/view/${result.user_id}/">${result.surname}</a>
            </td>
            <td>
                <a href="${@.context}/view/${result.user_id}/">${result.birthday}</a>
            </td>
            <td>
                <a href="${@.context}/view/${result.user_id}/">${result.gender}</a>
            </td>
            <td>
                <a href="${@.context}/view/${result.user_id}/">${result.province}</a>
            </td>
        </tr>
        [/#list]
    </tbody>
</table>
[/#if]