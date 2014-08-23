package com.myhandler.dao.entities;


/**
 * Created by anatoliy on 01.03.14.
 */
public class CountryEntity {
    private String code;
    private String name;
    private String continent;
    private String region;
    private float surfaceArea;
    private Short indepYear;
    private int population;
    private Float lifeExpectancy;
    private Float gnp;
    private Float gnpOld;
    private String localName;
    private String governmentForm;
    private String headOfState;
    private Integer capital;
    private String code2;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public float getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(float surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Short getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(Short indepYear) {
        this.indepYear = indepYear;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Float getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(Float lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public Float getGnp() {
        return gnp;
    }

    public void setGnp(Float gnp) {
        this.gnp = gnp;
    }

    public Float getGnpOld() {
        return gnpOld;
    }

    public void setGnpOld(Float gnpOld) {
        this.gnpOld = gnpOld;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryEntity that = (CountryEntity) o;

        if (population != that.population) return false;
        if (Float.compare(that.surfaceArea, surfaceArea) != 0) return false;
        if (capital != null ? !capital.equals(that.capital) : that.capital != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (code2 != null ? !code2.equals(that.code2) : that.code2 != null) return false;
        if (continent != null ? !continent.equals(that.continent) : that.continent != null) return false;
        if (gnp != null ? !gnp.equals(that.gnp) : that.gnp != null) return false;
        if (gnpOld != null ? !gnpOld.equals(that.gnpOld) : that.gnpOld != null) return false;
        if (governmentForm != null ? !governmentForm.equals(that.governmentForm) : that.governmentForm != null)
            return false;
        if (headOfState != null ? !headOfState.equals(that.headOfState) : that.headOfState != null) return false;
        if (indepYear != null ? !indepYear.equals(that.indepYear) : that.indepYear != null) return false;
        if (lifeExpectancy != null ? !lifeExpectancy.equals(that.lifeExpectancy) : that.lifeExpectancy != null)
            return false;
        if (localName != null ? !localName.equals(that.localName) : that.localName != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (continent != null ? continent.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (surfaceArea != +0.0f ? Float.floatToIntBits(surfaceArea) : 0);
        result = 31 * result + (indepYear != null ? indepYear.hashCode() : 0);
        result = 31 * result + population;
        result = 31 * result + (lifeExpectancy != null ? lifeExpectancy.hashCode() : 0);
        result = 31 * result + (gnp != null ? gnp.hashCode() : 0);
        result = 31 * result + (gnpOld != null ? gnpOld.hashCode() : 0);
        result = 31 * result + (localName != null ? localName.hashCode() : 0);
        result = 31 * result + (governmentForm != null ? governmentForm.hashCode() : 0);
        result = 31 * result + (headOfState != null ? headOfState.hashCode() : 0);
        result = 31 * result + (capital != null ? capital.hashCode() : 0);
        result = 31 * result + (code2 != null ? code2.hashCode() : 0);
        return result;
    }
}
