package guru.springframework.sfgpetclinic.Bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.PetTypeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;

    private final VetService vetService;

    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

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

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessica");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");

    }
}
