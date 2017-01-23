'use strict';

console.log('index');

import './styles/main.less';

import  'jquery';

import angular from 'angular';
import uiRouter from 'angular-ui-router';
import uiBootstrap from 'angular-ui-bootstrap';
import uiNotification from 'angular-ui-notification';

import DepartmentsService from "./content/department/departments.service";
import EmployeesService from "./content/employee/employees.service";
import ListDepartmentsComponent from "./content/departments.list.main/departments.controller";
import UpdateDepartmentComponent from "./content/department/department.controller";
import ListEmployeesComponent from "./content/employees.list/employees.controller";
import UpdateEmployeeComponent from "./content/employee/employee.controller";
import ModalRemoveDepartmentComponent from "./content/modals/department.remove/department.remove.modal";
import ModalRemoveEmployeeComponent from "./content/modals/employee.remove/employee.remove.modal";
import StringToNumber from "./content/services/StringToNumber.directive";
import routers from "./routes";

const app = 'departmentsApp';

angular.module(app, [
    uiRouter,
    uiBootstrap,
    uiNotification
])
    .config(routers)
    .service('DepartmentsService', DepartmentsService)
    .service('EmployeesService', EmployeesService)
    .component('listDepartments', ListDepartmentsComponent)
    .component('department', UpdateDepartmentComponent)
    .component('listEmployees', ListEmployeesComponent)
    .component('employee', UpdateEmployeeComponent)
    .component('removeDepartment', ModalRemoveDepartmentComponent)
    .component('removeEmployee', ModalRemoveEmployeeComponent)
    .directive('stringToNumber', StringToNumber);


angular.bootstrap(document, [app]);
