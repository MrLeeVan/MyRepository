
     /*
   * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
   *
   * Copyright 2017 摩码创想, support@momathink.com
    *
   * This file is part of Jiaowu_v1.0.
   * Jiaowu_v1.0 is free software: you can redistribute it and/or modify
   * it under the terms of the GNU Lesser General Public License as published by
   * the Free Software Foundation, either version 3 of the License, or
   * (at your option) any later version.
   *
   * Jiaowu_v1.0 is distributed in the hope that it will be useful,
   * but WITHOUT ANY WARRANTY; without even the implied warranty of
   * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   * GNU Lesser General Public License for more details.
   *
   * You should have received a copy of the GNU Lesser General Public License
   * along with Jiaowu_v1.0.  If not, see <http://www.gnu.org/licenses/>.
   *
   * 这个文件是Jiaowu_v1.0的一部分。
   * 您可以单独使用或分发这个文件，但请不要移除这个头部声明信息.
    * Jiaowu_v1.0是一个自由软件，您可以自由分发、修改其中的源代码或者重新发布它，
   * 新的任何修改后的重新发布版必须同样在遵守LGPL3或更后续的版本协议下发布.
   * 关于LGPL协议的细则请参考COPYING文件，
   * 您可以在Jiaowu_v1.0的相关目录中获得LGPL协议的副本，
   * 如果没有找到，请连接到 http://www.gnu.org/licenses/ 查看。
   *
   * - Author:摩码创想
   * - Contact: support@momathink.com
   * - License: GNU Lesser General Public License (GPL)
   */

package com.momathink.sys.account.model;

import java.util.ArrayList;
import java.util.List;

import com.momathink.common.annotation.model.Table;
import com.momathink.common.base.BaseModel;
import com.momathink.teaching.course.model.CoursePlan;

@Table(tableName="banci_course")
public class BanciCourse extends BaseModel<BanciCourse> {
	private static final long serialVersionUID = 1L;
	public static final BanciCourse dao = new BanciCourse();
	
	/**
	 * 获取班次的课程信息
	 * @author David
	 * @param classTypeId
	 * @param classId
	 */
	public List<BanciCourse> getCourseList(Integer classTypeId, Integer classId) {
		String typeSql = "select bc.*,c.COURSE_NAME from banci_course bc left join course c on bc.course_id=c.id where bc.type_id=? and bc.banci_id=0 GROUP BY c.id";
		List<BanciCourse> typeCourseList = dao.find(typeSql, classTypeId);
		String banciSql = "select bc.*,c.COURSE_NAME from banci_course bc left join course c on bc.course_id=c.id where bc.banci_id=? GROUP BY c.id ";
		List<BanciCourse> banciCourseList = dao.find(banciSql, classId);
		List<BanciCourse> courseList = new ArrayList<BanciCourse>();
		for(BanciCourse tc : typeCourseList){
			for(BanciCourse bc : banciCourseList){//获取班次课程数和已排课程数
				if(tc.getInt("course_id").equals(bc.getInt("course_id"))){
					tc.set("Id", bc.getInt("Id"));
					tc.set("lesson_num", bc.getInt("LESSON_NUM"));
					tc.put("coursePlanCount", CoursePlan.coursePlan.getClassYpkcClasshour(classId,bc.getInt("course_id")));
					break;
				}else{
					tc.set("lesson_num", 0);
					tc.put("coursePlanCount", 0);
				}
				boolean flag = false;
				for(BanciCourse typeCourse : typeCourseList){
					if(bc.getInt("course_id").equals(typeCourse.getInt("course_id"))){
						flag = true;
						break;
					}
				}
				if(!flag){
					bc.put("coursePlanCount", CoursePlan.coursePlan.getClassYpkcClasshour(classId,bc.getInt("course_id")));
					courseList.add(bc);
				}
			}
			courseList.add(tc);
		}
		return courseList;
	}

	/**
	 * 根据班次ID获取班次的课程
	 * @author David
	 * @param classOrderId
	 */
	public List<BanciCourse> findByClassOrderId(Integer classOrderId) {
		String sql = "select bc.*,c.COURSE_NAME from banci_course bc LEFT JOIN course c ON bc.course_id=c.Id WHERE bc.banci_id=? and lesson_num!=0";
		List<BanciCourse> banciCourseList = dao.find(sql, classOrderId);
		for(BanciCourse bc : banciCourseList){//获取班次课程数和已排课程数
			bc.put("coursePlanCount", CoursePlan.coursePlan.getClassYpkcClasshour(classOrderId,bc.getInt("course_id")));
		}
		return banciCourseList;
	}

}
