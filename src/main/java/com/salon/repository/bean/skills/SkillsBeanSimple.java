package com.salon.repository.bean.skills;

public class SkillsBeanSimple {

	private Long skillsId;
    private String name;
    
	public Long getSkillsId() {
		return skillsId;
	}
	public void setSkillsId(Long skillsId) {
		this.skillsId = skillsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((skillsId == null) ? 0 : skillsId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkillsBeanSimple other = (SkillsBeanSimple) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (skillsId == null) {
			if (other.skillsId != null)
				return false;
		} else if (!skillsId.equals(other.skillsId))
			return false;
		return true;
	}
    
}
