package kelgal.empleo.auto



import kelgal.empleo.auto.CertificadoController;
import kelgal.empleos.Certificado;

import org.junit.*
import grails.test.mixin.*

@TestFor(CertificadoController)
@Mock(Certificado)
class CertificadoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/certificado/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.certificadoInstanceList.size() == 0
        assert model.certificadoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.certificadoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.certificadoInstance != null
        assert view == '/certificado/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/certificado/show/1'
        assert controller.flash.message != null
        assert Certificado.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/certificado/list'

        populateValidParams(params)
        def certificado = new Certificado(params)

        assert certificado.save() != null

        params.id = certificado.id

        def model = controller.show()

        assert model.certificadoInstance == certificado
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/certificado/list'

        populateValidParams(params)
        def certificado = new Certificado(params)

        assert certificado.save() != null

        params.id = certificado.id

        def model = controller.edit()

        assert model.certificadoInstance == certificado
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/certificado/list'

        response.reset()

        populateValidParams(params)
        def certificado = new Certificado(params)

        assert certificado.save() != null

        // test invalid parameters in update
        params.id = certificado.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/certificado/edit"
        assert model.certificadoInstance != null

        certificado.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/certificado/show/$certificado.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        certificado.clearErrors()

        populateValidParams(params)
        params.id = certificado.id
        params.version = -1
        controller.update()

        assert view == "/certificado/edit"
        assert model.certificadoInstance != null
        assert model.certificadoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/certificado/list'

        response.reset()

        populateValidParams(params)
        def certificado = new Certificado(params)

        assert certificado.save() != null
        assert Certificado.count() == 1

        params.id = certificado.id

        controller.delete()

        assert Certificado.count() == 0
        assert Certificado.get(certificado.id) == null
        assert response.redirectedUrl == '/certificado/list'
    }
}
