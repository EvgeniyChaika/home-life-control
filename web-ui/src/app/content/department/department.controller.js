'use strict';

let vm;
const _$state = new WeakMap();
const _departmentsService = new WeakMap();

class UpdateDepartmentController {
    constructor($state, DepartmentsService) {
        vm = this;
        _$state.set(vm, $state);
        _departmentsService.set(vm, DepartmentsService);
        vm.departmentName = '';
        vm.idDepartment = null;

        if (vm.departmentData !== null) {
            vm.departmentName = vm.departmentData.data.departmentName;
            vm.idDepartment = vm.departmentData.data.idDepartment;
        }
        vm.errorEnterNameDep = [];
    }

    saveDepartment() {
        if (vm.departmentName === undefined) {
            vm.departmentName = '';
        }
        let saveDepComplete = (response) => {
            if (response === "") {
                _$state.get(vm).go('mainPage');
            } else {
                vm.errorEnterNameDep = response;
                _$state.get(vm).go('editDepartment');
            }
        };
        let saveDepFailed = () => {
            vm.errorEnterNameDep = ['There was a problem with update'];
        };

        _departmentsService.get(vm).saveDep(vm.idDepartment, vm.departmentName)
            .then(saveDepComplete)
            .catch(saveDepFailed);
    }

    toMainPage() {
        _$state.get(vm).go('mainPage');
    }
}

UpdateDepartmentController.$inject = ['$state', 'DepartmentsService'];

const UpdateDepartmentComponent = {
    controller: UpdateDepartmentController,
    controllerAs: 'updateDep',
    templateUrl: 'resources/app/content/department/update.department.html',
    bindings: {
        departmentData: '<'
    }
};

export default UpdateDepartmentComponent;