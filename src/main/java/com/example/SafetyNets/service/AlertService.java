package com.example.SafetyNets.service;

import com.example.SafetyNets.DTO.ChildrenAlertDto;
import com.example.SafetyNets.DTO.PersonDto;
import com.example.SafetyNets.DTO.PersonInfoDto;
import com.example.SafetyNets.model.*;
import com.example.SafetyNets.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.admin.SpringApplicationAdminMXBeanRegistrar;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import java.awt.image.ImageProducer;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import com.example.SafetyNets.Util.*;


@Service
public class AlertService {

    private static final Logger log = LoggerFactory.getLogger(AlertService.class);
    private final DataRepository repo;
    //Explication: SpringApplicationAdminMXBeanRegistrar ?
//    private final SpringApplicationAdminMXBeanRegistrar springApplicationAdminMXBeanRegistrar;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//    private final LocaleResolver localeResolver;

    public AlertService(DataRepository repo, SpringApplicationAdminMXBeanRegistrar springApplicationAdminMXBeanRegistrar, LocaleResolver localeResolver) {
        this.repo = repo;
//        this.springApplicationAdminMXBeanRegistrar = springApplicationAdminMXBeanRegistrar;
//        this.localeResolver = localeResolver;
    }


//method to getAllFirestations(): for No.(1)URL: /firestation?stationNumber=<number> eg.firestation?stationNumber=1
    public List<String> getPersonsByStationV1(String stationNumber) {

////V1 No.1 perfectly done with shortcut: get List<Person> then .getFirstName(); .getLastName(); .getPhone(); (.getAge();) .getAdultNum(); .getChildNum();
/*
//way1 for V1: enhanced for
        List<Person> getPersonsListByStation = new ArrayList<>();
        List<Firestation> firestations = repo.getFirestations();
        List<Person> persons = repo.getPersons();
        List<MedicalRecord> medicalRecords = repo.getMedicalRecords();
        //list of result to return
        List<String> getPersonByStationNew = new ArrayList<>();


        //Step1: to get the List of getPersonsListByStation
        //way1: enhanced for method
        for (Firestation firestation : firestations) {
            if (firestation.getStation().equals(stationNumber)) {
                for (Person person : persons) {
                    if (person.getAddress().equals(firestation.getAddress())) {
                        getPersonsListByStation.add(person);
                        //should the return value be saved in Data.json?
//                      repo.save();
                    }
                }
            }
        }

        //Step2 :method to calculate age and count the number of childre as well as the number of adult----------this method can be put in Util Package so that it can be reused in other class
        int childrenNumber = 0;
        int adultsNumber = 0;
        int age;
        //test: how to select the person do not correspond with the condition of selection?
        List<String> mishMatched = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (Person ps : getPersonsListByStation) {
            for (MedicalRecord mr : medicalRecords) {
                if (ps.getFirstName().equals(mr.getFirstName()) && ps.getLastName().equals(mr.getLastName())) {
                    LocalDate birth = LocalDate.parse(mr.getBirthdate(), formatter);
                    if (mr.getBirthdate() == null && birth.isAfter(now)) {
                        return null;
                    } else {
                        age = Period.between(birth, now).getYears();
                        if (age <= 18) {
                            childrenNumber++;
                        } else {
                            adultsNumber++;
                        }
                    }
                }
                //for test :how can i know the mismatched ?
                else {
                    mishMatched.add(ps.getFirstName());
                }
            }

        }

        //Step3: push the attributes wanted into the new List before returning it
        int total = getPersonsListByStation.size();
        int counter = 1;
        for (Person person : getPersonsListByStation) {
            getPersonByStationNew.add("**From Station:" + stationNumber + ". This is the " + counter + " of total Number of People" + total);
            getPersonByStationNew.add(person.getFirstName());
            getPersonByStationNew.add(person.getLastName());
            getPersonByStationNew.add(person.getAddress());
            getPersonByStationNew.add(person.getPhone());
            counter++;
//      getPersonByStationNew.add("From Station:"+stationNumber+". This is the"+((getPersonByStationNew.size()) / 4)+" of total Number of People"+total);
        }
        //push two new attributes into the new list before returning it
        getPersonByStationNew.add("**INFO:Number of Children: " + childrenNumber);
        getPersonByStationNew.add("**INFO:Number of Adults: " + adultsNumber);
        getPersonByStationNew.add("Test:" + persons.size());
        getPersonByStationNew.add("Test:" + medicalRecords.size());
        getPersonByStationNew.add("Test:" + total);
        getPersonByStationNew.add("Test:" + getPersonByStationNew.size());

////ShortCut*:best way to get the new list after pushing all the attributes wanted with method of Collections.unmodifiableList()
        return getPersonByStationNew;

 */

/* Steps4-5 is for personal training use---------wrong!

        //Step3: try to delete partially the attributes of the list getPersonListByStation from List<Person>----------not the best way to do, just try to practice with ArrayList, List,Collections,Stream()
        /*EXO2 for Step3
        //TODO: method to learn besides this project:remove the range of index for the List<getPersonsListByStation> by using the method .removeIf() of Collections
         //first way:use indexOfRange() of ArrayList to remove,not that good because the need to filter the elements is not always according to the index of List but according to the name of elements----here i have adjusted the order of elements wanted , so that i can use this method
//         if(!getPersonsListByStation.isEmpty()){
//             int oldSize = getPersonsListByStation.size();
//             int newSize;
//             int sizeWanted= 3;
//             if((newSize =oldSize-1)> sizeWanted){
//                 System.arraycopy(getPersonsListByStation.toArray(),sizeWanted+1,getPersonsListByStation.toArray(),sizeWanted,newSize-sizeWanted);
//                 //remove all the elements after the index of 3 in the List getPersonsListByStation;
//                 getPersonsListByStation.toArray()[oldSize=newSize] = null;
//             }
//         }

        //second way: use method of List with for loop
//            if(!getPersonsListByStation.isEmpty()){
//            int i;
//            int oldsize=getPersonsListByStation.size();
//            int sizeWanted = 3;
//            for(i=oldsize-1;i>sizeWanted;i--) {
//                    getPersonsListByStation.remove(i);
//                }
//            }

        //TODO : third way: use .removeIf() of Collections ---better: here it is not correct, should change!

//            if(!getPersonsListByStation.isEmpty()){
//                int oldSize = getPersonsListByStation.size()-1;
//                int sizeWanted = 3;
//                //way1: use the method .swap(List<Object>,i,j) of Collections to skip several elements from index of i to index of j----------to check if this one can change the size of a List<Object>
////                Collections.swap(getPersonsListByStation,sizeWanted+1,oldSize);
//                for(int i=oldSize;i>sizeWanted;i--){
//                  getPersonsListByStation.removeIf(person -> )
//                          //this code is for remove an element but not an attribute of the list.
////                        removeIf(Person-> persons.remove(getPersonsListByStation.get()));
//            }


        //Step4: after Step3 :add new attribute of List<String> with info of AgeCalculation for adult and children before add into the List of Person
//                Collections.fill(getPersonsListByStation,persons.get(i)); wrong way

                // Step* of thinking: method to create two new table to store the infos of number of children and adult, we can create just one new list to hold all the info wanted within the for loop, instead of creating outside
//        List<String[]> numberOfChildren = List.of(new String[]{"Children Number: " + childrenNumber});
//        List<String[]> numberOfAdult = List.of("Adult Number: "+adultsNumber});
//        List<String> infoNewList = new ArrayList<>();
//        infoNewList.set(4, numberOfChildren);
//        infoNewList.set(5, numberOfAdult);


        ////way1 for V1: use method of List.of() to create a new List<String> by concatenation with for loop
//        List<String> getPersonByStationNew = null;
//        for (Person ps : persons) {
//            getPersonByStationNew = List.of(ps.getFirstName(), ps.getLastName(), ps.getAddress(), ps.getPhone(), "Children Number" + adultsNumber, "Adult Number: " + adultsNumber);
//        }
        ////way2 for V1: create a New list of List<String> as ArrayList<>() to hold all String Object and use Collections.unmodifiable() to collect all String objects as elements

//            List<String> getPersonByStationNew = new ArrayList<>();
//            for (Person ps : getPersonsListByStation) {
//                getPersonByStationNew.add(ps.getFirstName());
//                getPersonByStationNew.add(ps.getLastName());
//                getPersonByStationNew.add(ps.getAddress());
//                getPersonByStationNew.add(ps.getPhone());
//                getPersonByStationNew.add("Number of Children: " + childrenNumber);
//                getPersonByStationNew.add("Number of Adults: " + adultsNumber);
//            }
//            return Collections.unmodifiableList(getPersonByStationNew);

         // Step5: return a list of person selected in the scope of a specific station number----only work with Step3-5
//        return getPersonByStationNew;

 */




//// V2 (1) No.2 with stream:without Dto (2)stream with Dto


//                 example
//        List<String> addresses = repo.getFirestations().stream()
//                .filter(f -> f.getStation().equals(station))
//                .map(Firestation::getAddress)
//                .toList();
//
//        return repo.getPersons().stream()
//                .filter(p -> addresses.contains(p.getAddress()))
//                .toList();
//
//
        //forth way: use stream.filter().collect(Collector.toList()) of Stream------ it is the best way
//// V2 (1) No.2 with stream:without Dto for : use stream without for loop
            //// wrong method No.1-------logic is not correct
         //Step1 for stream: use two List<String> to get a list of person live within the same station number as required and having the address corresponding with the List<Person>
            //use method of stream to filter the condition of the correspondence of the attribute of station in List<Firestation> and with the parameter in request of URL( stationNumber), logic bug: when collect .toList, we will get a list of address but not a List<Person>
//        List<String> getPersonByStationNumberTp = repo.getFirestations().stream()
//                .filter(firestation -> firestation.getStation().equals(stationNumber))
//                .map(Firestation::getAddress)
//                .collect(Collectors.toList());
            //logic and syntax bug: did not get a List<Person>, then here it becomes a Stream and can not be collected .toList any more
//        List<Person> getPersonByStationStream = repo.getPersons().stream()
//               .filter(person -> person.getAddress().contains(getPersonByStationNumberTp)); this syntax is not correct because the required datatype is as char sequence but not a List,
//                  .filter(person -> getPersonByStationNumberTp.contains(person.getAddress())).toList();
        //Done: how to use stream to collect certain attributes wanted and add new attribute separately or at once?---with .map()in creating several attributes within the map then use .Collect(collector) .toList to transfer to a new List or new Collections .----------as follow: (1)create new List<String> to collect certain attributes wanted (2): create a new List<PersonDto> to return all the attributes wanted by definning a new object Class
            //can not use this method: because the return value is List<String>, if i use stream() with constructor to create a new List<String>, the list of String object can not hold all the attributes get from List<Person> as attribute but only as normal concatenation of string, but the String can not be converted to a List<String>, which means the return value is not correct--------in situation that the return value is different, should avoid to use this , use TDO is much more better
////       wrong method No.2----------remove an element in List with method of List or Collection, is different from select several certain attributes in a List<Object>.
//        List<String> getPersonByStationNew1 = getPersonByStationStream.stream()
//               .map(person ->
//                       //Q: what is the difference if i use constructor to create a new String object? and what if i do not use the constructor but just select the attributes wanted?---------not much different
//                            new String(person.getFirstName() + person.getLastName() + person.getAddress() + person.getPhone())
//                       (person.getFirstName() + person.getLastName() + person.getAddress() + person.getPhone())
//               );
//        getPersonByStationNew1.add(String.valueOf(childrenNumber));
//        getPersonByStationNew1.add(String.valueOf(adultsNumber));
//        return  getPersonByStationNew1;

////    correct method with stream: (1)Create several List<String> to store or to print a list of String as string elements (2) can work with Dto---easier way (3) can use method of Stream(), like .filter(),map(),count(),toList(),to get directly the return value wanted
            //Step1: use stream() to filter() the firestation number of parameter with the List<Firestation>
            List<String> getAdressnAccordingParameter = repo.getFirestations().stream()
                    .filter(firestation -> firestation.getStation().equals(stationNumber))
                    .map(Firestation::getAddress)
                    .toList();
            //step2:get the List<Person> according to the address
            List<Person> getPersonsByStationNumberParameter = repo.getPersons().stream()
                    .filter(person -> getAdressnAccordingParameter.contains(person.getAddress()))


                    //here, can use .map() with constructor to create a new List<Person>, because List<Person>has its own attributes which should never be modified! to get and modify certain attributes, there will be two ways :(1)use TDO to create another version Entity which is adapted for the return value expected;(2) step by step, to create several same type of List by using stream method to filter() the conditions then create a new type which corresponds to the retune value expected, then at last by using for loop to print the result as wanted.
                        //(2):by creating several List<String> with for loop to print the attributes wanted
                            //here, if I use constructor to create a new List<Person>, but map it to several wanted attributes, that will be wrong.Even i selected the attributes wanted, others not wanted will still exist as Null value, because the type of object is the same. Only way to fix this, is to create a New class of Dto to store the attributes wanted then create a new List<PersonDto>to get the list of certain attibutes wanted.
//                    .map(person ->
//                            new Person(
//                                    person.getFirstName(),
//                                    person.getLastName(),
//                                    person.getAddress(),
//                                    person.getPhone())
//                            )
                    .toList();

            //step3.0 create a new List<String> to store the firstName and lastName of the selected getPersonsByStationNumberParameter as List<Person>
                /*  way1 for Step3.0: with for loop to create only one loop to hold four infos within one List<String>
            List<String> getFourAttributes = new ArrayList<>();
             for(int i=0;i<getPersonsByStationNumberParameter.size();i++){
                 getNames.add(getPersonsByStationNumberParameter.get(i).getFirstName());
                 getNames.add(getPersonsByStationNumberParameter.get(i).getLastName());
                 getNames.add(getPersonsByStationNumberParameter.get(i).getAddress());
                 getNames.add(getPersonsByStationNumberParameter.get(i).getPhone());
             }

                 */

                //way1 for Step3.0:with stream() to separate all info wanted into several List<String>
             List<String> getFirstNames = getPersonsByStationNumberParameter.stream()
                     .map(Person::getFirstName)
                     .toList();

             List<String> getLastNames = getPersonsByStationNumberParameter.stream()
                     .map(Person::getLastName)
                     .toList();

            List<String> getAddresses = getPersonsByStationNumberParameter.stream()
                    .map(Person::getAddress)
                    .toList();

            List<String> getPhones = getPersonsByStationNumberParameter.stream()
                    .map(Person::getPhone)
                    .toList();

            //step4: filter the List<MedicalRecords> by name corresponding with the List<Person>,

        /*way1 for Step4: to count the number of adult and children: by calling the method within the class or in package Util, or event write directly as follows
        String getBirthString = new String();
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(getBirthString,formatter);
        int childNumber=0;
        int adultNumber=0;
        List<Firestation> firestation = repo.getFirestations();
        List<MedicalRecord> medicalRecord = repo.getMedicalRecords();
        List<Person> person1 = repo.getPersons();
        for(Firestation fs: firestation){
            if(Objects.equals(fs.getStation(), stationNumber)){
                for(Person p: person1){
                    if (Objects.equals(p.getAddress(), fs.getAddress())){
                        for(MedicalRecord mr: medicalRecord){
                            if(Objects.equals(mr.getFirstName(),p.getFirstName())&&Objects.equals(mr.getLastName(),p.getLastName())){
                                if(CalculateAge(mr) > 18){
                                    adultNumber++;
                                }
                                else {
                                    childNumber++;
                                }
                            }
                        }
                    }
                }
            }
        }

         */

        /* way2 for Steps4: by creating two list to get the number of adult and children----works but not recommended
//        //to get the List<String> contains the number of adult and children
//        List<String> getBirthByNameWithMedicalRecords = repo.getMedicalRecords().stream()
//                    //here can recall the method of calculate Age which could be put in package of Util, to select
//                    .filter(medicalRecord -> getFirstNames.contains(medicalRecord.getFirstName()) && getLastNames.contains(medicalRecord.getLastName()))
//                    .map(MedicalRecord::getBirthdate
//                            )
//
//                    .toList();

//        long getAdultNumber = getBirthByNameWithMedicalRecords.stream()
//                .filter( birthdate -> (Period.between( LocalDate.parse(birthdate, formatter),LocalDate.now()).getYears())>18)
//                .count();

         */

        //way3 for Steps4: by calling the method and use method of stream to filter the condition and .count() to collect the info wanted----------best way
        long getAdultNumber = repo.getMedicalRecords().stream()
                .filter(medicalRecord -> getFirstNames.contains(medicalRecord.getFirstName()) && getLastNames.contains(medicalRecord.getLastName())&& CountAge.IsAdult(medicalRecord))
                .count();

        long getChildrenNumber = getPersonsByStationNumberParameter.size()-getAdultNumber;

            //step5:print all four attributes wanted from several List<String> and add another two attributes to the List at the end
        List<String> result = new ArrayList<>();
        for(int i=0;i<getPersonsByStationNumberParameter.size();i++){
            result.add(getFirstNames.get(i));
            result.add(getLastNames.get(i));
            result.add(getAddresses.get(i));
            result.add(getPhones.get(i));
        }
        result.add("AdultNumber: "+String.valueOf(getAdultNumber));
        result.add("ChildNumber: "+String.valueOf(getChildrenNumber));

        return  result;

        //TODO V2: for V1 return List<String> way(2) with Dto

        //TODO V3: for V1 return List<String>  use map() without Dto


        // TODO V4:for V1 return List<String>  with TDO to get the return value of a List<Person>


    }
    //V2 for V2: return map<String,Object> with Dto
    public Map<String, Object> getPersonsByStation(String stationNumber) {

        List<Person> personList= repo.getPersons();
        List<Firestation> firestationList = repo.getFirestations();
        List<MedicalRecord> medicalRecordList = repo.getMedicalRecords();

        Map<String,Object> result = new HashMap<>();
        int  numberOfAdults =0;
        int  numberOfChildren=0;

        ////v1: three for loop
        ////v2: with stream and Dto
        //step1: verify station number in List<Firestation> and List<Person> by matching address
        List<String> stationAddresses = firestationList.stream()
                .filter(firestation -> firestation.getStation().equals(stationNumber))
                        .map(Firestation::getAddress)
                                .toList();
        result.put("stationAddresses",stationAddresses);
        //conditionV1: person.getAddress not repeated as long as stationAddresses has one record of repeatation since the address and station are the same for Firestation
        List<PersonDto> getPersonsByAddresses = personList.stream()
                .filter(person -> stationAddresses.contains(person.getAddress()))
                        .map(person -> new PersonDto(
                                person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone()
                        ))
                                .toList();

        //step2: verify List<MedicalRecords> and List<Person> to get birthDate by matching firstName and lastName and //step3: method to calculate the number of Adults and children -------use .filter(anyMatch()) and .count() to calculate at one time!
        numberOfAdults = Math.toIntExact(medicalRecordList.stream()
                .filter(medicalRecord -> getPersonsByAddresses.stream()
                        .anyMatch(personDto -> Objects.equals(personDto.getFirstName(), medicalRecord.getFirstName()) && Objects.equals(personDto.getLastName(), medicalRecord.getLastName()) && CountAge.IsAdult(medicalRecord)))
                .count());

        numberOfChildren = Math.toIntExact(getPersonsByAddresses.size() - numberOfAdults);


        result.put("contacts", getPersonsByAddresses);
        result.put("numberOfAdults", numberOfAdults);
        result.put("numberOfChildren", numberOfChildren);
        return result;
    }

//TODO: for No.(2) URL: /childAlert?address=<address> :method to getAllChildren by searching address :getFirstName(); getLastName(); getAge();getFamilyMembers(); return Null(if no child found)
    ////V1 : return value as Map<String,Object>
public Map<String, Object> childrenAlerts(String address) {
        List<Person>  personList= repo.getPersons();
        List<MedicalRecord> medicalRecordList = repo.getMedicalRecords();

        Map<String,Object> result = new HashMap<>();

        //way1: with stream() and Dto ---TODO: i have cut this to several stream() and converted many times the type of object for List, Error can generated when the logic is too long! Waiting to find a way to simplify my stream---------it can be replaced with for loop and if...else virifying conditions as well!
            //step1: get List<Person> getPersonsByAddress
         List<Person> getPersonsByAddress = personList.stream()
                 .filter(person -> person.getAddress().equals(address))
                 .toList();

         //for later use: if this List contains children, then to return List<ChildreAlertDto>, if no children found, this list should be used for the if..else.. verification
         List<MedicalRecord> getChildrenListByAddress = medicalRecordList.stream()
                 .filter(medicalRecord -> getPersonsByAddress.stream()
                                 .anyMatch(person -> Objects.equals(person.getFirstName(), medicalRecord.getFirstName()) && Objects.equals(person.getLastName(), medicalRecord.getLastName())&&(!CountAge.IsAdult(medicalRecord)))
                                 )
                 .toList();
        /* replaced by the simpler one below
        List<String> getChildrenListByAddressLastNames = medicalRecordList.stream()
            .filter(medicalRecord -> getPersonsByAddress.stream()
                    .anyMatch(person -> Objects.equals(person.getFirstName(), medicalRecord.getFirstName()) && Objects.equals(person.getLastName(), medicalRecord.getLastName())&&(!CountAge.IsAdult(medicalRecord)))
            )
                .map(MedicalRecord::getLastName)
            .toList();

         */

        List<String> getChildrenListByAddressLastNames = getChildrenListByAddress.stream()
                .map(MedicalRecord::getLastName)
                .toList();

         //TODO: the return value of this list is not correct, for example, for address"1509 Culver St" should have two children but everyone has five familyMembers, my return has only two same , not correct!
        //get familyMembers as List<MedicalRecord>
        List<MedicalRecord> familyMembersFromMedicalRecord = medicalRecordList.stream()
                .filter(medicalRecord -> getChildrenListByAddressLastNames.contains(medicalRecord.getLastName()))
                .toList();

        //change List<MedicalRecord> to List<Person>
        List<Person> familyMembers = personList.stream()
                        .filter(person -> familyMembersFromMedicalRecord.stream()
                                .anyMatch(medicalRecord ->Objects.equals(medicalRecord.getFirstName(),person.getFirstName())&&Objects.equals(medicalRecord.getLastName(),person.getLastName()))
                        )
                .toList();

//        List<ChildrenAlertDto> getChildrenByAddressDto = medicalRecordList.stream()
//                .filter(medicalRecord -> getPersonsByAddress.stream()
//                        .anyMatch(person -> Objects.equals(person.getFirstName(), medicalRecord.getFirstName())&& Objects.equals(person.getLastName(), medicalRecord.getLastName())&& !CountAge.IsAdult(medicalRecord)))
//                                .map(medicalRecord->new ChildrenAlertDto(
//                                        medicalRecord.getFirstName(),medicalRecord.getLastName(), String.valueOf(CountAge.CalculateAge(medicalRecord)), familyMembers
//                                ))
//                .toList();


    List<ChildrenAlertDto> getChildrenByAddressDto = getChildrenListByAddress.stream()
            .map(medicalRecord->new ChildrenAlertDto(
                    medicalRecord.getFirstName(),medicalRecord.getLastName(), String.valueOf(CountAge.CalculateAge(medicalRecord)), familyMembers
            ))
            .toList();

       result.put("ChildrenAtThisAddress", getChildrenByAddressDto);

       //for no children return null, else return the List<ChildrenAlertDto>
       if(getChildrenListByAddress.isEmpty()){
           return null;
       }else {
        return result;
}
       ////V2: return the List<ChildrenAlertDto>--------
            //way1: TODO: try stream() with for and if
            //way2: use for loop ------better for test?

    }

//Done: for No.(3) URL : /phoneAlert?firestation=<number>

    //for No.(3)URL : method to getAllPhones(): for No.(3)URL: /phoneAlert?firestation=<number> eg.phoneAlert?firestation=1
/*
    //V1 : return value of List<String> , by using stream() without Dto:
    public List<String> getPhonesByStation(String station) {
       /*
        List<String> getPhonesListByStation = new ArrayList<>();
        List<Firestation> firestations = repo.getFirestations();
        List<Person> persons = repo.getPersons();
    //way1: with stream() : step by step
        List<String> AddressList = firestations.stream()
                .filter(firestation -> firestation.getStation().equals(station))
                .map(Firestation::getAddress)
                .toList();


        getPhonesListByStation = persons.stream()
                .filter(person -> AddressList.contains(person.getAddress()))
                .map(Person::getPhone)
                .distinct()// to filter the double data
                .toList();

        return getPhonesListByStation;
 */

/*V1: way2
        //V1: way 2: return value of List<String> with for loop ----can test with
        List<Person>  personList= repo.getPersons();
        List<Firestation> firestationList = repo.getFirestations();
        List<String> result = new ArrayList<>();

        for (Firestation firestation : firestationList) {
            if(firestation.getStation().equals(station)){
                for (Person person : personList) {
                    if (firestation.getAddress().equals(person.getAddress())) {
                        result.add(person.getPhone());
                    }
                }
            }
        }
        return  result;
}

         */

        //V2: with return value of Map<String,String> with stream()
        public Map<String, String> getPhonesByStation(String stationNumber) {
            List<Person> personList= repo.getPersons();
            List<Firestation> firestationList = repo.getFirestations();

            Map<String,String> result = new HashMap<>();

            List<String> AddressList = firestationList.stream()
                    .filter(firestation -> firestation.getStation().equals(stationNumber))
                    .map(Firestation::getAddress)
                        .toList();

            String getPhonesByAddresses= personList.stream()
                    .filter(person -> AddressList.contains(person.getAddress()))
                    .map(Person::getPhone)
                            .collect(Collectors.joining(","));
            result.put("stationNumber",stationNumber);
            result.put("phones",getPhonesByAddresses);
            return  result;

        }





//TODO: for No.(4)URL /fire?address=<address>: method to :(i) get List<Person> by searching address ; person.firestName(); person.lastName; (ii) method to get List<MedicalRecords> ;then the method of person.getAge(String birthdate); then method of List<String> of medications; then method of List<String> of allergies;

//TODO: for No.(5) URL: /flood/stations?stations=<list>: (i) getAllFirestations() as a List<Firestation>; (ii) optional exo for me: List<Person> then create List<Family> by stream().filter( person.lastName);(iii) then with stream().filter(address) to .collect() person.firstName(); person.lastName(); (IV) method to get List<MedicalRecords> (V) method of getAge(medicalRecord.birthdate()); (VI) method of returning List<String> Medications ; method of returning List<String> Allergies;

//TODO: for No.(6) URL:  /personInfo?firstName=&lastName= (i) get List<Person> by searching firstName, lastName (ii) returning person.address; person.email; person.firstName; person.lastName;(iii)get List<MedicalRecord>, then method to getAge(medicalRecord.birthdate());method of returning two List<String> Medications + List<String> Allergies; or create a new List of this two List if it is possible as List<MedicationAllergies> ;(iv) use stream().filter and .collect()
    //// V1 : with return value of Map<String,Object>
    //way1:stream() with Dto
    public Map<String, Object> getPersonInfo(String firstName, String lastName) {

        List<Person> personList= repo.getPersons();
        List<MedicalRecord> medicalRecordList = repo.getMedicalRecords();

        Map<String,Object> result = new HashMap<>();

        List<String> medicationsByNames = medicalRecordList.stream()
                .filter(person -> personList.stream()
                        .anyMatch(medicalRecord ->  Objects.equals(medicalRecord.getFirstName(),person.getFirstName() )&& Objects.equals(medicalRecord.getLastName(),person.getLastName())))
                .map(medicalRecord -> String.valueOf (medicalRecord.getMedications()))
                .toList();

        List<String> allergiesByNames = medicalRecordList.stream()
                .filter(person -> personList.stream()
                        .anyMatch(medicalRecord ->  Objects.equals(medicalRecord.getFirstName(),person.getFirstName() )&& Objects.equals(medicalRecord.getLastName(),person.getLastName())))
                .map(medicalRecord -> String.valueOf (medicalRecord.getAllergies()))
                .toList();

        //TODO: to check the return of age
        int ageByNames = medicalRecordList.stream()
                .map(CountAge::CalculateAge);


        List<PersonInfoDto> personInfoDtos = personList.stream()
                .filter(person -> person.getFirstName().equals(firstName)&& person.getLastName().equals(lastName))
//                .filter(person -> medicalRecordList.stream()
//                        .anyMatch(medicalRecord ->  Objects.equals(medicalRecord.getFirstName(),person.getFirstName() )&& Objects.equals(medicalRecord.getLastName(),person.getLastName())))

                .map(person -> new PersonInfoDto(
                        person.getFirstName(),person.getLastName(),person.getAddress(),ageByNames,person.getEmail(),medicationsByNames,allergiesByNames)
                )
                .toList();


        return  result;

    }


    //for No.(7): method to getAllEmails(): for No.(7)URL: /communityEmail?city=<city> eg.city=Culver---------done
    // communityEmail?city=Paris
    public List<String> getEmailsByCity(String city) {
        /* way0: for normal
        for(int i=0; i<persons.size(); i++) {
            if(city.equals(persons.get(i).getCity())){
                emails.add(persons.get(i).getEmail());
            }
        }
        return emails;

         */
        //way1: enhanced for loop
        List<String> emails = new ArrayList<>();
        List<Person> persons = repo.getPersons();

        for (Person p : persons) {
            if (p.getCity().equals(city)) {
                emails.add(p.getEmail());
                //save?
            }
        }
        return emails;



/* way2: stream().
//        return repo.getPersons().stream()
//                .filter(p -> p.getCity().equalsIgnoreCase(city))
//                .map(Person::getEmail)
//                .distinct()
//                .toList();

 */


    }




    /*Util:  method to calculate the ages
    int CalculateAge(MedicalRecord mr){
        return Period.between( LocalDate.parse(mr.getBirthdate(),formatter),LocalDate.now()).getYears();
    }

    boolean IsAdult(MedicalRecord mr)
    {
        return Period.between( LocalDate.parse(mr.getBirthdate(),formatter), LocalDate.now()).getYears() > 18;
    }

     */
}

