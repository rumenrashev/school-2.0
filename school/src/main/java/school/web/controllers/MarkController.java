package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.model.binding.MarkBindingModel;
import school.model.service.MarkServiceModel;
import school.service.MarkService;
import school.service.StudentService;
import school.service.SubjectService;

import java.util.List;

@Controller
@RequestMapping("/marks")
public class MarkController extends BaseController {

    private final MarkService markService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public MarkController(ModelMapper modelMapper,
                          MarkService markService,
                          StudentService studentService,
                          SubjectService subjectService) {
        super(modelMapper);
        this.markService = markService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public String getMarks(Model model) {
        Long studentId = (Long) model.getAttribute("studentId");
        Long subjectId = (Long) model.getAttribute("subjectId");
        List<MarkServiceModel> marks = markService.getMarksByStudentAndSubject(studentId, subjectId);
        model.addAttribute("student", studentService.getStudentById(studentId));
        model.addAttribute("subject", subjectService.getSubjectById(subjectId));
        model.addAttribute("marks", markService.getMarksByStudentAndSubject(studentId,subjectId));
        model.addAttribute("avg", markService.getStudentAverageMark(marks));
        return "marks-all";
    }

    @PostMapping("/all")
    public String marksPost(Long studentId, Long subjectId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("studentId", studentId);
        redirectAttributes.addFlashAttribute("subjectId", subjectId);
        return redirect("/marks/all");
    }

    @PostMapping("/add")
    public String addMark(MarkBindingModel bindingModel, RedirectAttributes redirectAttributes) {
        MarkServiceModel serviceModel = modelMapper.map(bindingModel, MarkServiceModel.class);
        markService.addMark(serviceModel);
        redirectAttributes.addFlashAttribute("studentId", bindingModel.getStudentId());
        redirectAttributes.addFlashAttribute("subjectId", bindingModel.getSubjectId());
        return redirect("/marks/all");
    }

    @DeleteMapping("/delete")
    public String delete(Long markId, Long subjectId, Long studentId, RedirectAttributes redirectAttributes) {
        markService.deleteMark(markId);
        redirectAttributes.addFlashAttribute("studentId", studentId);
        redirectAttributes.addFlashAttribute("subjectId", subjectId);
        return redirect("/marks/all");
    }

    @PutMapping("/edit")
    public String edit(MarkBindingModel bindingModel, RedirectAttributes redirectAttributes) {
        MarkServiceModel serviceModel = modelMapper.map(bindingModel, MarkServiceModel.class);
        markService.editMark(serviceModel);
        redirectAttributes.addFlashAttribute("studentId", bindingModel.getStudentId());
        redirectAttributes.addFlashAttribute("subjectId", bindingModel.getSubjectId());
        return redirect("/marks/all");
    }
}
