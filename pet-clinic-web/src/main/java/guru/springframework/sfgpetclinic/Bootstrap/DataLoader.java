package guru.springframework.sfgpetclinic.Bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {

            loadData();

        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality saveRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality saveSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality saveDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Lara");
        owner1.setLastName("Yudilla");
        owner1.setAddress("Jl.Melati no.21");
        owner1.setCity("Jakarta");
        owner1.setTelephone("085795130397");

        Pet larasCat = new Pet();
        larasCat.setPetType(savedCatPetType);
        larasCat.setOwner(owner1);
        larasCat.setBirthDate(LocalDate.now());
        larasCat.setName("Si Kuning");
        owner1.getPets().add(larasCat);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Jane");
        owner2.setAddress("Jl.Tb Simatupang no.41");
        owner2.setCity("Jakarta");
        owner2.setTelephone("089993773732");

        Pet fionasDog = new Pet();
        fionasDog.setPetType(savedDogPetType);
        fionasDog.setOwner(owner2);
        fionasDog.setBirthDate(LocalDate.now());
        fionasDog.setName("Spike");
        owner2.getPets().add(fionasDog);

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(saveRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessica");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(saveSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
