'use strict';

const routers = ($stateProvider, $urlRouterProvider) => {
    console.log('routes');
    $urlRouterProvider.otherwise('/');
    $stateProvider
        .state('mainPage', {
            url: '/',
            template: '<list-departments departments-data="$resolve.departments"></list-departments>',
            title: 'main',
            resolve: {
                departments: (DepartmentsService) => DepartmentsService.getAllDepartments()
            }
            // resolve: {
            //     departments: (DepartmentsService) => DepartmentsService.getAllDepartments(null,null,false)
            // }
            // resolve: {
            //     departments: ['DepartmentsService', (DepartmentsService) => {
            //         return DepartmentsService.getAllDepartments(null, null, false);
            //     }]
            // }
        })
        .state('editDepartment', {
            url: '/add-editDepartment/idDepartment=:idDepartment',
            template: '<department department-data="$resolve.department"></department>',
            title: 'department',
            resolve: {
                department: (DepartmentsService, $stateParams) => ($stateParams.idDepartment) ? DepartmentsService.findByIdDepartment($stateParams.idDepartment) : null
            }
        })
        .state('listEmployees', {
            url: '/listEmployees/idDepartment=:idDepartment',
            template: '<list-employees department-data="$resolve.department" employees-data="$resolve.employees"></list-employees>',
            title: 'employeesList',
            resolve: {
                department: (DepartmentsService, $stateParams) => ($stateParams.idDepartment) ? DepartmentsService.findByIdDepartment($stateParams.idDepartment) : null,
                employees: (EmployeesService, $stateParams) => ($stateParams.idDepartment) ? EmployeesService.getAllEmployees($stateParams.idDepartment) : null
            }
        })
        .state('editEmployee', {
            url: '/listEmployees/idDepartment=:idDepartment&add-editEmployee/idEmployee=:employeeId',
            template: '<employee department-data="$resolve.department" employee-data="$resolve.employee"></employee>',
            title: 'employee',
            resolve: {
                department: (DepartmentsService, $stateParams) => ($stateParams.idDepartment) ? DepartmentsService.findByIdDepartment($stateParams.idDepartment) : null,
                employee: (EmployeesService, $stateParams) => ($stateParams.employeeId) ? EmployeesService.findById($stateParams.employeeId) : null
            }
        });
};

export default routers;