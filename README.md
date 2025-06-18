# TallerW – Sistema Web de Donaciones

Este proyecto consiste en una plataforma web desarrollada en Java que permite gestionar el proceso de donación de alimentos entre donantes y beneficiarios. Fue desarrollado como parte de un trabajo colaborativo, implementando buenas prácticas de arquitectura backend, pruebas automatizadas e integración continua.

## ✍️ Contribuciones personales (Theratoffqa)

- Implementación completa de la lógica CRUD para Donantes, Beneficiarios, Donaciones y Solicitudes.
- Desarrollo de controladores (`Controller`), modelos (`Model`), repositorios (`Repository`) e interfaces de servicio (`Service`).
- Configuración inicial del proyecto con conexión a **MySQL**.
- Refactorización del backend para desacoplar la lógica con interfaces e inyecciones de dependencias.
- Integración de pruebas unitarias y de integración:
  - Tests para controladores y servicios (`ControllerTest`, `ServiceTest`)
  - Tests para repositorios (`RepositoryTest`)
- Configuración y mantenimiento del pipeline CI/CD con **Jenkins**:
  - Jenkinsfile para compilación automática y despliegue
  - Plugins para cobertura de código
  - Configuración de análisis estático con **SonarQube**
  - Pruebas funcionales automatizadas con **Postman/Newman**
- Solución de errores de mantenimiento, optimización del código y mejoras de seguridad (cambio de tokens, puertos, encoding UTF-8, limpieza de librerías).

## 🔧 Tecnologías utilizadas

- Java 17  
- Spring Boot  
- Maven  
- MySQL  
- Jenkins  
- SonarQube  
- JUnit  
- Postman / Newman  
- Git

## 📁 Repositorio original

[github.com/CarlosAscueOrosco/TallerW](https://github.com/CarlosAscueOrosco/TallerW)

---
