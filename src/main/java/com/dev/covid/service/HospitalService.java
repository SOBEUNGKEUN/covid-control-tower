package com.dev.covid.service;

import com.dev.covid.DTO.HospitalDTO;
import com.dev.covid.model.Hospital;
import com.dev.covid.model.HospitalRoom;
import com.dev.covid.repository.HospitalRepository;
import com.dev.covid.repository.HospitalRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository repository;
    @Autowired
    private HospitalRoomRepository hospitalRoomRepository;

    public List<Hospital> findAll() {
        return repository.findAll();
    }

    public Hospital save(HospitalDTO hospitalDTO) throws Exception {
        Hospital hospital = Hospital
                .builder()
                .hospitalId(hospitalDTO.getHospitalId())
                .hospitalName(hospitalDTO.getHospitalName())
                .hospitalPatientnum(hospitalDTO.getHospitalPatientnum())
                .hospitalRoomlimit(hospitalDTO.getHospitalRoomlimit())
                .build();
        return repository.save(hospital);
    }

    public List<Hospital> update(Hospital hospital) {
        final Optional<Hospital> findHospital = repository.findById((hospital.getHospitalId()));

        findHospital.ifPresent(updateHospital -> {
            updateHospital.setHospitalName(hospital.getHospitalName());
            updateHospital.setHospitalPatientnum((hospital.getHospitalPatientnum()));
            updateHospital.setHospitalRoomlimit(hospital.getHospitalRoomlimit());

            repository.save(updateHospital);
        });
        return repository.findAll();
    }

    public List<Hospital> delete(Long id) {
        final Optional<Hospital> findHospital = repository.findById(id);

        findHospital.ifPresent(hospital -> {
            repository.delete(hospital);
        });
        return repository.findAll();
    }
}
