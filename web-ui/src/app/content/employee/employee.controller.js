'use strict';

let vm;
const _$state = new WeakMap();
const _employeesService = new WeakMap();

class UpdateEmployeeController {
    constructor($state, EmployeesService) {
        vm = this;
        _$state.set(vm, $state);
        _employeesService.set(vm, EmployeesService);
        vm.employeeId = null;
        vm.employeeName = '';
        vm.employeeEmail = '';
        vm.employeeSalary = '';
        vm.employeeRegister = '';

        vm.departmentName = vm.departmentData.data.departmentName;
        vm.idDepartment = vm.departmentData.data.idDepartment;

        if (vm.employeeData !== null && vm.employeeData.data !== null) {
            vm.employeeId = vm.employeeData.data.employeeId;
            vm.employeeName = vm.employeeData.data.employeeName;
            vm.employeeEmail = vm.employeeData.data.employeeEmail;
            vm.employeeSalary = vm.employeeData.data.employeeSalary;
            vm.employeeRegister = new Date(vm.employeeData.data.employeeRegister);
        }

        vm.errorEmployeeName = [];
        vm.errorEmployeeEmail = [];
        vm.errorEmployeeSalary = [];
        vm.errorEmployeeDate = [];
    }

    correctFields() {
        if (vm.employeeName === undefined) {
            vm.employeeName = '';
        }
        if (vm.employeeEmail === undefined) {
            vm.employeeEmail = '';
        }
        if (vm.employeeSalary === undefined) {
            vm.employeeSalary = '';
        }
        if (vm.employeeRegister === undefined) {
            vm.employeeRegister = '';
        }
    }

    saveEmployee() {
        vm.correctFields();
        let saveEmplComplete = (response) => {
            if (response === "") {
                _$state.get(vm).go('listEmployees', {
                    idDepartment: vm.idDepartment
                });
            } else {
                if (response.errorNameEmpl !== undefined) {
                    vm.errorEmployeeName = response.errorNameEmpl;
                }
                if (response.errorEmailEmpl !== undefined) {
                    vm.errorEmployeeEmail = response.errorEmailEmpl;
                }
                if (response.errorSalaryEmpl !== undefined) {
                    vm.errorEmployeeSalary = response.errorSalaryEmpl;
                }
                if (response.errorDateRegistrationEmpl !== undefined) {
                    vm.errorEmployeeDate = response.errorDateRegistrationEmpl;
                }
                _$state.get(vm).go('editEmployee');
            }
        };
        let saveEmplFailed = () => {
            vm.errorEmployeeName = ['There was a problem with update'];
        };

        _employeesService.get(vm).saveEmpl(vm.employeeId, vm.employeeName, vm.employeeEmail,
            vm.employeeSalary, vm.employeeRegister, vm.idDepartment)
            .then(saveEmplComplete)
            .catch(saveEmplFailed);

    }

    toListEmployees() {
        _$state.get(vm).go('listEmployees', {
            idDepartment: vm.idDepartment
        })
    }
}

UpdateEmployeeController.$inject = ['$state', 'EmployeesService'];

const UpdateEmployeeComponent = {
    controller: UpdateEmployeeController,
    controllerAs: 'updateEmpl',
    templateUrl: 'resources/app/content/employee/update.employee.html',
    bindings: {
        departmentData: '<',
        employeeData: '<'
    }
};

export default UpdateEmployeeComponent;