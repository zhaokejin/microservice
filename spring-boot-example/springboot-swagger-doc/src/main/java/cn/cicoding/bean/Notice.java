package cn.cicoding.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cicoding.cn
 * @description
 * @date 2019/12/19
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel("公告")
public class Notice {
	/**
	 * ID
	 */
	@ApiModelProperty("id")
	private Integer id;

	/**
	 * 公告内容
	 */
	@ApiModelProperty("公告内容")
	private String content;

}
