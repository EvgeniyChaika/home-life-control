'use strict';

let vm;
const _$state = new WeakMap();
const _$uibModal = new WeakMap();
const _employeesService = new WeakMap();

class ListEmployeesController {
    constructor($state, $uibModal, EmployeesService, $scope) {
        vm = this;
        _$state.set(vm, $state);
        _$uibModal.set(vm, $uibModal);
        _employeesService.set(vm, EmployeesService);
        vm.departmentName = $scope.$parent.$resolve.department.data.departmentName;
        vm.idDepartment = $scope.$parent.$resolve.department.data.idDepartment;
        vm.employees = $scope.$parent.$resolve.employees.data;
    }

    editEmployee(employeeId) {
        _$state.get(vm).go('editEmployee', {
            idDepartment: vm.idDepartment,
            employeeId: employeeId
        });
    }

    removeEmployee(employeeId) {
        vm.modalInstance = _$uibModal.get(vm).open({
            component: 'removeEmployee',
            resolve: {employeeId}
        });

        vm.modalInstance.result.then((result) => {
                if (result.message === 'OK') {
                    _employeesService.get(vm).getAllEmployees(vm.idDepartment)
                        .then((response) => {
                            vm.employees = response.data;
                        });
                } else {
                    console.log(result.message);
                }
            }
        );
    }

    addEmployee() {
        _$state.get(vm).go('editEmployee', {
            idDepartment: vm.idDepartment,
            employeeId: null
        });
    }

    toMainPage() {
        _$state.get(vm).go('mainPage');
    }
}

ListEmployeesController.$inject = ['$state', '$uibModal', 'EmployeesService', '$scope'];

const ListEmployeesComponent = {
    controller: ListEmployeesController,
    controllerAs: 'listEmpl',
    template: require('./list.employees.html'),
    bindings: {
        departmentData: '<',
        employeesData: '<'
    }
};

export default ListEmployeesComponent;