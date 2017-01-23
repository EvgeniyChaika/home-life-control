'use strict';

let vm;
const _$state = new WeakMap();
const _departmentsService = new WeakMap();

class ModalRemoveDepartmentController {
    constructor($state, DepartmentsService) {
        vm = this;
        _$state.set(vm, $state);
        _departmentsService.set(vm, DepartmentsService);
        vm.idDepartment = vm.resolve.idDepartment;
    }

    remove() {
        _departmentsService.get(vm).removeDep(vm.idDepartment)
            .success(() => {
                vm.modalInstance.close({
                    message: 'OK'
                });
            })
            .error((error) => {
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

ModalRemoveDepartmentController.$inject = ['$state', 'DepartmentsService'];

const ModalRemoveDepartmentComponent = {
    controller: ModalRemoveDepartmentController,
    controllerAs: 'modalDep',
    templateUrl: './resources/app/content/modals/department.remove/department.remove.modal.html',
    bindings: {
        modalInstance: "<",
        resolve: '<'
    }
};

export default ModalRemoveDepartmentComponent;