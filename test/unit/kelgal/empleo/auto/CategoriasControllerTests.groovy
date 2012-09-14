package kelgal.empleo.auto

import kelgal.empleo.auto.CategoriasController;
import kelgal.empleos.Categorias



@TestFor(CategoriasController)
@Mock(Categorias)
class CategoriasControllerTests {

	
    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/categorias/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.categoriasInstanceList.size() == 0
        assert model.categoriasInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.categoriasInstance != null
    }

    void testSave() {
        controller.save()

        assert model.categoriasInstance != null
        assert view == '/categorias/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/categorias/show/1'
        assert controller.flash.message != null
        assert Categorias.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/categorias/list'

        populateValidParams(params)
        def categorias = new Categorias(params)

        assert categorias.save() != null

        params.id = categorias.id

        def model = controller.show()

        assert model.categoriasInstance == categorias
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/categorias/list'

        populateValidParams(params)
        def categorias = new Categorias(params)

        assert categorias.save() != null

        params.id = categorias.id

        def model = controller.edit()

        assert model.categoriasInstance == categorias
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/categorias/list'

        response.reset()

        populateValidParams(params)
        def categorias = new Categorias(params)

        assert categorias.save() != null

        // test invalid parameters in update
        params.id = categorias.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/categorias/edit"
        assert model.categoriasInstance != null

        categorias.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/categorias/show/$categorias.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        categorias.clearErrors()

        populateValidParams(params)
        params.id = categorias.id
        params.version = -1
        controller.update()

        assert view == "/categorias/edit"
        assert model.categoriasInstance != null
        assert model.categoriasInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/categorias/list'

        response.reset()

        populateValidParams(params)
        def categorias = new Categorias(params)

        assert categorias.save() != null
        assert Categorias.count() == 1

        params.id = categorias.id

        controller.delete()

        assert Categorias.count() == 0
        assert Categorias.get(categorias.id) == null
        assert response.redirectedUrl == '/categorias/list'
    }
}
