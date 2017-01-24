'use strict';

let vm;
const _$state = new WeakMap();
const _employeesService = new WeakMap();

class UpdateEmployeeController {
    constructor($state, EmployeesService, $scope) {
        vm = this;
        _$state.set(vm, $state);
        _employeesService.set(vm, EmployeesService);
        vm.employeeId = null;
        vm.employeeName = '';
        vm.employeeEmail = '';
        vm.employeeSalary = '';
        vm.employeeRegister = '';

        vm.departmentName = $scope.$parent.$resolve.department.data.departmentName;
        vm.idDepartment = $scope.$parent.$resolve.department.data.idDepartment;

        if ($scope.$parent.$resolve.employee !== null && $scope.$parent.$resolve.employee.data !== null) {
            vm.employeeId = $scope.$parent.$resolve.employee.data.employeeId;
            vm.employeeName = $scope.$parent.$resolve.employee.data.employeeName;
            vm.employeeEmail = $scope.$parent.$resolve.employee.data.employeeEmail;
            vm.employeeSalary = $scope.$parent.$resolve.employee.data.employeeSalary;
            vm.employeeRegister = new Date($scope.$parent.$resolve.employee.data.employeeRegister);
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

UpdateEmployeeController.$inject = ['$state', 'EmployeesService', '$scope'];

const UpdateEmployeeComponent = {
    controller: UpdateEmployeeController,
    controllerAs: 'updateEmpl',
    template: require('./update.employee.html'),
    bindings: {
        departmentData: '<',
        employeeData: '<'
    }
};

export default UpdateEmployeeComponent;