# Fragrance store

## Software versions used

The software versions used in application development are: 
Microsoft Dynamics 365 Business Central 2022 wave 2 (BC21)
Android Studio Arctic Fox | 2020.3.1 Patch 2


## Steps for running the applications

### Web application:
1. Download Microsoft Dynamics 365 Business Central 2022 wave 2 https://www.microsoft.com/en-us/download/details.aspx?id=104636 
2. Install BC21 as follows:
- setup.exe -> Next -> Accept
- Advanced installation options -> Choose installation -> Custom
- Microsoft Dynamics 365 Business Central -> Run all from my computer
- select ports, database, instance name, etc.
- for "Authentication" choose initially: "Windows"
3. Install Visual Studio Code
4. Install the extension: "AL Language"
5. Open the project (using VS Code) and modify the "launch.json" file according to what was chosen during installation
6. Download the script "GenerateSSCertificate.ps1": https://www.navuser.com/create-self-signed-certificate-using-windows-powershell-ise/
7. Reproduce the following steps to change the authentication in NavUserPassword: https://www.sauravdhyani.com/2020/07/msdyn365bc-modern-client-with.html
8. Run the project


### Mobile application:
1. Download Android Studio: https://developer.android.com/studio
2. Install Java 1.8.0_241
3. Use the following version for Gradle JDK: 7.0.2
4. Install an emulator with android version newer than 4.4.2
5. Run the project


## Minimum requirements

The operating systems to be presented are only required to run the **web application**.

- Windows 11 Pro, Enterprise, or Education (32-bit & 64-bit editions)
- Windows 10 Pro, Enterprise, or Education (32-bit & 64-bit editions)
- Windows Server 2022 (Datacenter, Standard)
- Windows Server 2019 (Datacenter, Standard)
- Windows Server, minimum version 1809
- Windows Server 2016 Standard, Essentials, or Datacenter.