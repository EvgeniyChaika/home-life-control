'use strict';

let vm;
const _$state = new WeakMap();
const _$uibModal = new WeakMap();
const _employeesService = new WeakMap();

class ListEmployeesController {
    constructor($state, $uibModal, EmployeesService) {
        vm = this;
        _$state.set(vm, $state);
        _$uibModal.set(vm, $uibModal);
        _employeesService.set(vm, EmployeesService);
        vm.departmentName = vm.departmentData.data.departmentName;
        vm.idDepartment = vm.departmentData.data.idDepartment;
        vm.employees = vm.employeesData.data;
    }

    editEmployee(employeeId) {
        _$state.get(vm).go('editEmployee', {
            idDepartment: vm.idDepartment,
            employeeId: employeeId
        });
    }

    removeEmployee(employeeId) {
        let modalInstance = _$uibModal.get(vm).open({
            component: 'removeEmployee',
            resolve: {employeeId}
        });

        modalInstance.result.then((result) => {
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

ListEmployeesController.$inject = ['$state', '$uibModal', 'EmployeesService'];

const ListEmployeesComponent = {
    controller: ListEmployeesController,
    controllerAs: 'listEmpl',
    templateUrl: 'resources/app/content/employees.list/list.employees.html',
    bindings: {
        departmentData: '<',
        employeesData: '<'
    }
};

export default ListEmployeesComponent;