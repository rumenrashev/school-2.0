package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.anotation.PageTitle;
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
    private final SubjectService subjectService;
    private final StudentService studentService;

    @Autowired
    public MarkController(ModelMapper modelMapper,
                          MarkService markService,
                          SubjectService subjectService,
                          StudentService studentService) {
        super(modelMapper);
        this.markService = markService;
        this.subjectService = subjectService;
        this.studentService = studentService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('TEACHER','STUDENT')")
    @PageTitle(value = "Оценки")
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
    @PreAuthorize("hasAnyAuthority('TEACHER','STUDENT')")
    public String marksPost(Long studentId, Long subjectId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("studentId", studentId);
        redirectAttributes.addFlashAttribute("subjectId", subjectId);
        return redirect("/marks/all");
    }


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String addMark(MarkBindingModel bindingModel, RedirectAttributes redirectAttributes) {
        MarkServiceModel serviceModel = modelMapper.map(bindingModel, MarkServiceModel.class);
        markService.addMark(serviceModel);
        redirectAttributes.addFlashAttribute("studentId", bindingModel.getStudentId());
        redirectAttributes.addFlashAttribute("subjectId", bindingModel.getSubjectId());
        return redirect("/marks/all");
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String delete(Long markId, Long subjectId, Long studentId, RedirectAttributes redirectAttributes) {
        markService.deleteMark(markId);
        redirectAttributes.addFlashAttribute("studentId", studentId);
        redirectAttributes.addFlashAttribute("subjectId", subjectId);
        return redirect("/marks/all");
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String edit(MarkBindingModel bindingModel, RedirectAttributes redirectAttributes) {
        MarkServiceModel serviceModel = modelMapper.map(bindingModel, MarkServiceModel.class);
        markService.editMark(serviceModel);
        redirectAttributes.addFlashAttribute("studentId", bindingModel.getStudentId());
        redirectAttributes.addFlashAttribute("subjectId", bindingModel.getSubjectId());
        return redirect("/marks/all");
    }
}
