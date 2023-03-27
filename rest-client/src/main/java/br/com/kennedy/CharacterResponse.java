package br.com.kennedy;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CharacterResponse {
    
    @JsonProperty("id")
    private String idx;
    private String name;
    private String status;
    private String species;
    private String image;
    private List<String> episode;
    /*public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public List<String> getEpisode() {
        return episode;
    }
    public void setEpisode(List<String> episode) {
        this.episode = episode;
    }
    */
    @Override
    public String toString() {
        return "CharacterResponse [idx=" + idx + ", name=" + name + ", status=" + status + ", species=" + species
                + ", image=" + image + ", episode=" + episode + "]";
    }
    public List<String> getEpisode() {
        return episode;
    }
  
    
    
    
    
}
