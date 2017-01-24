'use strict';

let vm;
const _$state = new WeakMap();
const _employeesService = new WeakMap();

class ModalRemoveEmployeeController {
    constructor($state, EmployeesService, $scope) {
        vm = this;
        _$state.set(vm, $state);
        _employeesService.set(vm, EmployeesService);
        vm.idEmployee = $scope.$parent.$resolve.employeeId;
    }

    remove() {
        _employeesService.get(vm).removeEmpl(vm.idEmployee)
            .then(() => {
                vm.modalInstance.close({
                    message: 'OK'
                });
            })
            .catch((error) => {
                console.error(error);
                vm.modalInstance.close({
                    message: 'Error'
                });
            });
    }

    cancel() {
        vm.modalInstance.close({
            message: 'Cancel'
        });
    }
}

ModalRemoveEmployeeController.$inject = ['$state', 'EmployeesService', '$scope'];

const ModalRemoveEmployeeComponent = {
    controller: ModalRemoveEmployeeController,
    controllerAs: 'modalEmpl',
    template: require('./employee.remove.html'),
    bindings: {
        modalInstance: "<",
        resolve: '<'
    }
};

export default ModalRemoveEmployeeComponent;