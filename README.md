### Project Summary: Ticket System for Cloud Provisioning

**Purpose:**
This project aims to develop a web application to facilitate the Cloud team's efficient request and delivery of Virtual Machines (VMs) to users. The application will replace the manual tracking of VM requests done via email and Microsoft Excel, automating the entire process for better management and scalability.

**Scope:**
The scope includes building a complete Ticket System for Cloud Provisioning, including user login, VM request submission, and role-based access management, with end-to-end testing and deployment.

**Key Features:**
- **User Registration & Login:** Provides user authentication and access based on roles (ADMIN, HOD, CLOUD, USER).
- **VM Request Management:** Users can submit VM requests, and HODs can accept or reject these requests.
- **Change Password:** Users can securely update their credentials.

**Architecture & Interfaces:**
- **Frontend:** ReactJS will be used for the user interface.
- **Backend:** The system will follow the Model-View-Controller (MVC) pattern and use REST-compliant web services over HTTPS for client-server communication.
- **Data Handling:** JSON, CSV, and ASCII will be supported for data formats.
- **Security:** All communications will be secured using SSL/TLS protocols.

**Operating Environment:**
The system will be compatible with Linux and Windows server environments and support modern web browsers like Safari 7+ and Chrome 44+.

**Non-functional Requirements:**
- **Performance:** Resource requirements should be minimized with industry best practices.
- **Security:** Proper database backup and recovery mechanisms will ensure data safety in case of failures.
- **Maintainability & Usability:** The system should be easy to maintain and meet the usability needs of all types of users.

This project aims to streamline VM provisioning, making the process more efficient and scalable for future growth.
