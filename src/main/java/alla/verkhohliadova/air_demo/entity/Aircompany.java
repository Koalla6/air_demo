package alla.verkhohliadova.air_demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Aircompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "aircompany")
    private List<Airplane> airplanesOfThisCompany = new ArrayList<>();

    //
    @JsonIgnore
    @OneToMany(mappedBy = "aircompany")
    private List<Flight> flights = new ArrayList<>();
    //

    private String companyType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY")
    private LocalDate foundedAt;
}
