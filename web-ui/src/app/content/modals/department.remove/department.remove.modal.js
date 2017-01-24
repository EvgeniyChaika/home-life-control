'use strict';

let vm;
const _$state = new WeakMap();
const _departmentsService = new WeakMap();

class ModalRemoveDepartmentController {
    constructor($state, DepartmentsService, $scope) {
        vm = this;
        _$state.set(vm, $state);
        _departmentsService.set(vm, DepartmentsService);
        vm.idDepartment = $scope.$parent.$resolve.idDepartment;
    }

    remove() {
        _departmentsService.get(vm).removeDep(vm.idDepartment)
            .then((response) => {
                console.log(response.data);
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

ModalRemoveDepartmentController.$inject = ['$state', 'DepartmentsService', '$scope'];

const ModalRemoveDepartmentComponent = {
    controller: ModalRemoveDepartmentController,
    controllerAs: 'modalDep',
    template: require('./department.remove.modal.html'),
    bindings: {
        modalInstance: '<',
        resolve: '<'
    }
};

export default ModalRemoveDepartmentComponent;