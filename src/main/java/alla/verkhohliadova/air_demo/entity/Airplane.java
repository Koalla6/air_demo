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
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String factorySerialNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "aircompanyId")
    private Aircompany aircompany;

    @JsonIgnore
    @OneToMany (mappedBy = "airplane")
    private List<Flight> numberOfFlights = new ArrayList<>();

    private Integer flightDistance;

    private Integer fuelCapacity;

    private String type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY")
    private LocalDate createdAt;

}
