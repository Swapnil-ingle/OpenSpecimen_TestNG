### Introduction
A TestNG based testing framework for OpenSpecimen.

### How to setup source-code?
1. ```git clone https://github.com/Swapnil-ingle/OpenSpecimen_TestNG```
2. ```gradle clean```
3. ```gradle eclipse```
4. Import the directory into IDE as a Java project.

### How to run?
1. Change directory to where the project is cloned on local. 
> ```cd $src_dir```

2. Make sure to fill the *"$src_dir/src/test/resources/testing.properties"* file with appropriate values.

Property   |   Description 
------------- |:-------------
**os.username** | OpenSpecimen user's login name (This is used to authenticate when calling APIs)
**os.password** | The mentioned user's password (This is used to authenticate when calling APIs)
**os.url** | OpenSpecimen's instance URL (Which the framework would be testing)
**csv.input_date_fmt** | Date format for date type values in CSV
**csv.user_file** | The input file path for user module
**pre_req.users_json_file** | The JSON file path which contains the prerequisite for user module

3. ```gradle clean```
4. ```gradle test```

---
> **Note:** 
>> 1. The TestNG output report is generated in `$src_dir/testngOutput/custom-emailable-report.html`
>>> This report shows only the highlight of the test run. It should be embedded in the email being sent.
>> 2. The Gradle output report is generated in `$src_dir/build/reports/tests/index.html`
>>> This is the detailed report.
---

### Workflow

1. **DataProvider** reads the data from input CSV and convert it into corresponding **RowDetail** object.
2. This **RowDetail** object is then feeded to it's corresponding **@test** (case) method.
3. Before the **@test** method is executed, its **PreReqProcessor** is invoked, where it reads the **pre-req.module_json_file** and builts the needed entities.
4. This **@test** method then reads the DTO and calls appropriate APIs.
5. The response from the called API is submitted to ```Utility.processResponse(resp)``` to validate the received statusCode against the TcType.
6. After all the testCases are completed the **TestNGCustomEmailableReport** class is invoked which generates the *custom-emailable-report.html* as required.
