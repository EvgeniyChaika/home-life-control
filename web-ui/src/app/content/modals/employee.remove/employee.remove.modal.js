'use strict';

let vm;
const _$state = new WeakMap();
const _employeesService = new WeakMap();

class ModalRemoveEmployeeController {
    constructor($state, EmployeesService) {
        vm = this;
        _$state.set(vm, $state);
        _employeesService.set(vm, EmployeesService);
        vm.idEmployee = vm.resolve.employeeId;
    }

    remove() {
        _employeesService.get(vm).removeEmpl(vm.idEmployee)
            .success(() => {
                vm.modalInstance.close({
                    message: 'OK'
                });
            })
            .error((error) => {
                console.error(error);
            });
    }

    cancel() {
        vm.modalInstance.close({
            message: 'Cancel'
        });
    }
}

ModalRemoveEmployeeController.$inject = ['$state', 'EmployeesService'];

const ModalRemoveEmployeeComponent = {
    controller: ModalRemoveEmployeeController,
    controllerAs: 'modalEmpl',
    templateUrl: './resources/app/content/modals/employee.remove/employee.remove.html',
    bindings: {
        modalInstance: "<",
        resolve: '<'
    }
};

export default ModalRemoveEmployeeComponent;