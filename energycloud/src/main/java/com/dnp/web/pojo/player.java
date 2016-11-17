package com.dnp.web.pojo;

import java.util.Date;

/**
 * Created by wode on 7/6/16.
 */
public class player {
    /*
    CREATE TABLE `player` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '',
  `sex` int(11) NOT NULL,
  `born` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sport_id` int(11) NOT NULL,
  `coach_id` int(11) NOT NULL,
  `age_group` varchar(100) DEFAULT NULL,
  `org_id` int(11) NOT NULL,
  `status` int(11) DEFAULT '1',
  `isimportant` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1324 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT
     */

    public player() {
    }

    @Override
    public String toString() {
        return "player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", born=" + born +
                ", sportid=" + sportid +
                ", coachid=" + coachid +
                ", agegroup='" + agegroup + '\'' +
                ", orgid=" + orgid +
                ", status=" + status +
                ", isimportant=" + isimportant +
                ", sportname='" + sportname + '\'' +
                ", coachname='" + coachname + '\'' +
                ", orgname='" + orgname + '\'' +
                '}';
    }

    public String getSportname() {
        return sportname;
    }

    public void setSportname(String sportname) {
        this.sportname = sportname;
    }

    public String getCoachname() {
        return coachname;
    }

    public void setCoachname(String coachname) {
        this.coachname = coachname;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    private int id;
    private String name;
    private int sex;
    private Date born;

    public player(int id, String name, int sex, Date born, int sportid, int coachid, String agegroup, int orgid, int status, int isimportant, String sportname, String coachname, String orgname) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.born = born;
        this.sportid = sportid;
        this.coachid = coachid;
        this.agegroup = agegroup;
        this.orgid = orgid;
        this.status = status;
        this.isimportant = isimportant;
        this.sportname = sportname;
        this.coachname = coachname;
        this.orgname = orgname;
    }

    private int sportid;
    private int coachid;
    private String agegroup;
    private int orgid;
    private int status;
    private int isimportant;
    private String sportname;
    private String coachname;
    private String orgname;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBorn() {
       return com.dnp.util.DateUtil.dateToStr(born, com.dnp.util.Def.DATE_FORMATER);
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public int getSportid() {
        return sportid;
    }

    public void setSportid(int sportid) {
        this.sportid = sportid;
    }

    public int getCoachid() {
        return coachid;
    }

    public void setCoachid(int coachid) {
        this.coachid = coachid;
    }

    public String getAgegroup() {
        return agegroup;
    }

    public void setAgegroup(String agegroup) {
        this.agegroup = agegroup;
    }

    public int getOrgid() {
        return orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsimportant() {
        return isimportant;
    }

    public void setIsimportant(int isimportant) {
        this.isimportant = isimportant;
    }

}
