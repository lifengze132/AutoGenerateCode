<#--package ${package};-->

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${className} {

    <#list columns as column>
        private ${column.type} ${column.name};
    </#list>

}
